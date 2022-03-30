package com.rshub.javafx.ui.model.walking

import com.rshub.api.pathing.WalkHelper
import com.rshub.api.pathing.web.edges.Edge
import com.rshub.api.pathing.web.edges.strategies.EdgeTileStrategy
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleSetProperty
import javafx.collections.FXCollections
import tornadofx.ViewModel

class WebWalkingModel : ViewModel() {

    val autoUpdate = bind { SimpleBooleanProperty(this, "auto_update", true) }

    val vertices = bind { SimpleSetProperty<VertexModel>(this, "vertices", FXCollections.observableSet(mutableSetOf())) }

    val edges = bind { SimpleSetProperty<EdgeModel>(this, "edges", FXCollections.observableSet(mutableSetOf())) }

    val selectedVertex = bind { SimpleObjectProperty<VertexModel>(this, "selected_vertex") }
    val selectedEdge = bind { SimpleObjectProperty<EdgeModel>(this, "selected_edge") }

    fun reload() {
        val graph = WalkHelper.getGraph()
        vertices.set(FXCollections.observableSet(graph.getAllVertices().mapIndexed { index, graphVertex -> VertexModel(index, graphVertex.tile)}.toMutableSet()))
        graph.getAllEdges().forEach { addEdgeModel(it) }
    }

    private fun addEdgeModel(edge: Edge) {
        val vmFrom = vertices.find { it.tile.get() == edge.from.tile }!!
        val vmTo = vertices.find { it.tile.get() == edge.to.tile }!!
        val strategy = when (edge.strategy) {
            is EdgeTileStrategy -> EdgeStrategy.TILE
            else -> EdgeStrategy.TILE
        }
        vmFrom.edges.add(EdgeModel(vmFrom, vmTo, strategy))
    }

    fun update() {
        val graph = WalkHelper.getGraph()
        graph.setVertices(vertices.map { it.toVertex() })
        graph.setEdges(vertices.flatMap { it.edges }.map { it.toEdge() })
    }
}