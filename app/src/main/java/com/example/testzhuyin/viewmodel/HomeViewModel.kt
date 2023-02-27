package com.example.testzhuyin.viewmodel

import com.amap.api.maps.model.LatLng
import com.example.testzhuyin.base.BaseViewModel
import com.example.testzhuyin.contract.HomeContract
import com.example.testzhuyin.repo.HomeRepo
import kotlinx.coroutines.Dispatchers

class HomeViewModel : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {
    override fun createInitialState(): HomeContract.State {
        return HomeContract.State(
            isLoading = false,
            uiSettings = HomeRepo.initMapUISettings(),
            mapProperties = HomeRepo.initMapProperties(),
            ethnicityDataList = emptyList()
        )
    }

    override fun handleEvents(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.OpenDetailPage -> {
                setEffect {
                    HomeContract.Effect.Navigation.OpenDetailPage(event.latLng,event.iconName)
                }
            }
        }
    }
    //加载
    init {
        asyncLaunch(Dispatchers.IO) {
            val dataList = HomeRepo.initEthnicityDataList()
            setState { copy(ethnicityDataList = dataList) }
        }
    }

    fun openDetailPage(latLng: LatLng, iconName: String) {
        setEvent(HomeContract.Event.OpenDetailPage(latLng,iconName))
    }


}