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
	 * Gets the corresponding KTON body of a key, potentially `null`.
	 *
	 * @param key The key of the body.
	 */
	operator fun invoke(key: String) = get(key) as KTON

	/**
	 * Gets the corresponding KTON array of an index.
	 *
	 * @param index The index of the array (of declaration order).
	 */
	operator fun get(index: Int) = arrays[index]

	/**
	 * Establishes (maps) a value to the current string in a KTON.
	 */
	operator fun <V : Any> String.rangeTo(value: V) {
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
	 * @param body The body representing the new array KTON.
	 */
	fun <O> KTON.arr(body: KTON.() -> O) {
		arrays.add(kton(body))
	}

}

/**
 * Creates a KTON using the supplied body.
 *
 * @param body The body of the KTON used to declare more KTONs or values.
 */
inline fun <T> kton(body: KTON.() -> T): KTON {
	val kton = KTON()
	kton.body()
	return kton
}