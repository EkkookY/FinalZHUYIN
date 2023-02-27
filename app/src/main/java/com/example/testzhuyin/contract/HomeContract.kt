package com.example.testzhuyin.contract

import com.amap.api.maps.model.LatLng
import com.melody.map.gd_compose.poperties.MapProperties
import com.melody.map.gd_compose.poperties.MapUiSettings
import com.example.testzhuyin.base.state.IUiEffect
import com.example.testzhuyin.base.state.IUiEvent
import com.example.testzhuyin.base.state.IUiState
import com.example.testzhuyin.model.EthnicityDataModel

class HomeContract {
    sealed class Event : IUiEvent {
        data class OpenDetailPage(val latLng: LatLng,val iconName: String):Event()
    }

    data class State(
        val isLoading: Boolean,
        val uiSettings: MapUiSettings,
        val mapProperties: MapProperties,
        val ethnicityDataList: List<EthnicityDataModel>
    ) : IUiState

    sealed class Effect : IUiEffect {
        sealed class Navigation {
            data class OpenDetailPage(val latLng: LatLng,val iconName: String): Effect()
        }
    }
}