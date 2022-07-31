package com.msharibahmed.closedpullrequests.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msharibahmed.closedpullrequests.models.Item
import com.msharibahmed.closedpullrequests.repository.ClosedPrRepository
import com.msharibahmed.closedpullrequests.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val closedPrRepository: ClosedPrRepository,
) : ViewModel() {
    companion object {
        const val REPO_NAME = "jitsi-meet"
        const val USER = "jitsi"
    }


    private val _dataState: MutableLiveData<DataState<List<Item>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Item>>>
        get() = _dataState

    fun callApi() {
        viewModelScope.launch {
            closedPrRepository.getClosedPrs(USER, REPO_NAME)
                .onEach { dataState ->
                    _dataState.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }

}

