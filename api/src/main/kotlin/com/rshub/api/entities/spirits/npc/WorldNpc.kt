package com.rshub.api.entities.spirits.npc

import com.rshub.api.definitions.CacheHelper
import com.rshub.api.entities.spirits.WorldSpirit
import com.rshub.definitions.NpcDefinition
import kraken.plugin.api.*

class WorldNpc(private val npc: Npc) : WorldSpirit {

    val id get() = npc.id
    val health get() = npc.health
    val transformedId get() = npc.transformedId

    override val serverIndex get() = npc.serverIndex
    override val isMoving get() = npc.isMoving
    override val activeStatusBars: Map<Int, Boolean> get() = npc.activeStatusBars
    override val statusBarFill: Map<Int, Float> get() = npc.statusBarFill
    override val animationId get() = npc.animationId
    override val isAnimationPlaying get() = npc.isAnimationPlaying
    override val interactingIndex get() = npc.interactingIndex
    override val directionOffset: Vector2 get() = npc.directionOffset

    override val scenePosition: Vector3 get() = npc.scenePosition
    override val globalPosition: Vector3i get() = npc.globalPosition

    val def: NpcDefinition by lazy { CacheHelper.getNpc(id) }

    val name: String get() = def.name
    val options: Array<String?> get() = def.options
    val combatLevel: Int get() = def.combatLevel

    val varp: ConVar? get() {
        return Client.getConVarById(def.varp)
    }
    val varbit: Int get() = CacheHelper.getVarbitValue(def.varbit)

    inline operator fun <reified T> get(key: Int) : T? {
        val map = def.params.map ?: return null
        if(!map.containsKey(key)) return null
        return map[key]!! as T
    }
}