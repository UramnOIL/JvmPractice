package com.uramnoil.nukkit.nt4k.plugin

import cn.nukkit.Server
import cn.nukkit.plugin.Plugin
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T: Plugin> getPlugin() = Server.getInstance().pluginManager.plugins.values.filterIsInstance<T>().first()

class PluginDelegate<P: Plugin>(private val plugin: P) : ReadOnlyProperty<Any, P> {
	override fun getValue(thisRef: Any, property: KProperty<*>) = plugin
}

class PluginDelegateCheckEnabled<P: Plugin>(private val plugin: P) : ReadOnlyProperty<Any, P?> {
	override fun getValue(thisRef: Any, property: KProperty<*>) = if(plugin.isEnabled) plugin else null
}

inline fun <reified P: Plugin> delegatePlugin() = PluginDelegate(getPlugin<P>())

inline fun <reified P: Plugin> delegatePluginCheckEnabled() = PluginDelegateCheckEnabled(getPlugin<P>())

interface APIProvider<A> {
	val api: A
}

class APIDelegate<A>(private val api: A) : ReadOnlyProperty<Any, A> {
	override fun getValue(thisRef: Any, property: KProperty<*>): A = api
}

inline fun <reified A> getAPI() = Server.getInstance().pluginManager.plugins.values.filterIsInstance<APIProvider<*>>().map{ it.api }.filterIsInstance<A>().first()