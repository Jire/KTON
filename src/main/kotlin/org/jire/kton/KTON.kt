/*
 * Copyright 2016 Thomas Nappo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jire.kton

import java.util.*

/**
 * Represents an instance of KTON (Kotlin object notation).
 *
 * @author Thomas Nappo
 */
class KTON {

	/**
	 * Maps string keys with their values.
	 */
	private val vars = HashMap<String, Any>()

	/**
	 * Stores the array KTONs by their insertion order.
	 */
	private val arrays = ArrayList<KTON>()

	/**
	 * Gets the corresponding value of a key, potentially `null`.
	 *
	 * @param key The key of the value.
	 */
	operator fun get(key: String) = vars[key]

	/**
	 * Gets the corresponding KTON body of a key.
	 *
	 * @param key The key of the body.
	 */
	operator fun invoke(key: String) = this[key]!! as KTON

	/**
	 * Gets the corresponding KTON array of an index.
	 *
	 * @param index The index of the array (of declaration order).
	 */
	operator fun get(index: Int) = arrays[index]

	/**
	 * Establishes (maps) a value to the current string in a KTON.
	 */
	infix fun <V : Any> String.to(value: V) {
		vars[this] = value
	}

	/**
	 * Establishes (maps) a body to the current string in a KTON.
	 *
	 * @param body The body representing the new KTON.
	 */
	operator fun <O> String.invoke(body: KTON.() -> O) {
		vars[this] = kton(body)
	}

	/**
	 * Establishes an array to the current KTON.
	 *
	 * @param bodies The bodies representing elements in the new array.
	 */
	operator fun <O> String.get(vararg bodies: KTON.() -> O) {
		bodies.forEach { arrays.add(kton(it)) }
	}

}

/**
 * Creates a KTON using the supplied body.
 *
 * @param body The body of the KTON used to declare more KTONs or values.
 */
inline fun <O> kton(body: KTON.() -> O): KTON {
	val kton = KTON()
	kton.body()
	return kton
}