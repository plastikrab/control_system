package com.example.control_system.view.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.control_system.R
import com.example.control_system.data.models.scenarioModel.Scenario
import com.example.control_system.data.models.scenarioModel.ScenarioData
import com.example.control_system.data.models.scenarioModel.UpdateChoiceReport
import com.example.control_system.data.models.scenarioModel.UpdateChoiceReports
import com.example.control_system.model.data.models.scenarioModel.Scenario
import com.example.control_system.model.data.models.scenarioModel.ScenarioData
import com.example.control_system.model.data.models.scenarioModel.UpdateChoiceReport
import com.example.control_system.model.network.LecturesServ
import com.example.control_system.view.components.CheckboxReport
import com.example.control_system.view.components.RadioButtonReport
import com.example.control_system.view.components.TaskCard
import com.example.control_system.view.components.TextBlock
import com.example.control_system.view.theme.BordersColour
import com.example.control_system.view.theme.TextBlack
import com.example.control_system.viewmodel.MainReportsScreenViewModel


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainReportsScreen(
    viewModel: MainReportsScreenViewModel = MainReportsScreenViewModel(),
    scenarioList: ScenarioData,
    startScenario : (Scenario) -> Unit,
    onConnectionError: () -> Unit,
    saveScenario : (Scenario) -> Unit,
    showToast : (String) -> Unit
) {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    val interFont = GoogleFont("Inter")
    val interFamily = FontFamily(Font(googleFont = interFont, fontProvider = provider))


    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val savedScenariosList: MutableList<Scenario> = mutableListOf()


    LazyColumn(
    ) {
        Log.d("MyLog","MainScreen open")
        items(scenarioList.scenarios){scenario ->
            TaskCard(
                scenario,
                openBottomSheet = {
                    viewModel.reportData.value = it
                    showBottomSheet = true
                },
                startScenario = {
                    savedScenariosList.add(it)
                    startScenario(it)
                },
                finishScenario = {

                },
                showToast = {
                    showToast(it)
                }
            )
        }
    }


    if (showBottomSheet && viewModel.reportData.value != null) {
        ModalBottomSheet(
            modifier = Modifier
                .size(726.dp),
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        ) {
            Text(
                text = viewModel.reportData.value?.report?.title ?: "No Data",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight(700),
                    color = TextBlack
                )
            )
            Text(
                text = viewModel.reportData.value?.report?.description ?: "No Data",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, top = 20.dp, bottom = 10.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = interFamily,
                    fontWeight = FontWeight(800),
                    color = TextBlack
                )
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp)
                .background(BordersColour)
                .height(1.dp)
            )


            //Choice report
            if (viewModel.reportData.value?.report?.type == "choiceReports"){
                val choiceList = mutableListOf<UpdateChoiceReport>()


                Log.d("MyLog", "RadioButton opening")
                RadioButtonReport(
                    report = viewModel.reportData.value!!,
                    selected = {
                        Log.d("MyLog", "Clicked")
                        choiceList.clear()
                        var i = 0
                        viewModel.reportData.value!!.report.choiceReports.forEach{ item ->
                            item.answer = item.text == it
                            viewModel.reportData.value!!.doneStatus = true

                            i++
                            choiceList.add(
                                UpdateChoiceReport(
                                    viewModel.reportData.value!!.choiceReportsAssigned!![i-1]._id,
                                    item.answer
                                )
                            )
                        }
                        viewModel.reportData.value!!.report.choiceReports.forEach{ item ->
                            Log.d("MyLog", item.text + " " + item.answer)
                        }
                        viewModel.reportData.value!!.doneStatus = true
                        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.forEach{ savedScenario ->
                            savedScenario.reportsAssigned.forEach { savedReport ->
                                if (savedReport._id == viewModel.reportData.value!!._id){
                                    com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios[
                                        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.indexOf(
                                            savedScenario)].reportsAssigned[
                                        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios[
                                            com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.indexOf(
                                                savedScenario)].reportsAssigned.indexOf(
                                            savedReport)] =
                                        viewModel.reportData.value!!
                                }

                            }
                        }
                        LecturesServ.updateChoiceReports(
                            UpdateChoiceReports(
                                viewModel.reportData.value!!._id,
                                choiceList
                            ),
                            onConnectionError = {
                                onConnectionError()
                            }
                        )
                    }
                )
            }


            //CheckBox reports
            if (viewModel.reportData.value?.report?.type == "choiceReportsMany"){
                val choiceList = mutableListOf<UpdateChoiceReport>()


                Log.d("MyLog", "CheckBox opening")
                CheckboxReport(
                    report = viewModel.reportData.value!!,
                    onChange = {
                        choiceList.clear()
                        var i = 0
                        viewModel.reportData.value!!.report.choiceReports.forEach{ item ->
                            item.answer = it[i]
                            i++
                            choiceList.add(
                                UpdateChoiceReport(
                                    viewModel.reportData.value!!.choiceReportsAssigned!![i-1]._id,
                                    item.answer
                                )
                            )
                        }
                        viewModel.reportData.value!!.report.choiceReports.forEach{ item ->
                            Log.d("MyLog", item.text + " " + item.answer)
                        }

                        viewModel.reportData.value!!.doneStatus = true
                        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.forEach{ savedScenario ->
                            savedScenario.reportsAssigned.forEach { savedReport ->
                                if (savedReport._id == viewModel.reportData.value!!._id){
                                    com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios[
                                        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.indexOf(
                                            savedScenario)].reportsAssigned[
                                        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios[
                                            com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.indexOf(
                                                savedScenario)].reportsAssigned.indexOf(
                                            savedReport)] =
                                        viewModel.reportData.value!!
                                }

                            }
                        }
                        LecturesServ.updateChoiceReports(
                            UpdateChoiceReports(
                                viewModel.reportData.value!!._id,
                                choiceList
                            ),
                            onConnectionError = {
                                onConnectionError()
                            }
                        )
                    }
                )
            }


            //Text report
            if (viewModel.reportData.value?.report?.type == "text"){
                TextBlock(
                    blocks = viewModel.reportData.value!!.report.fileBlocks.toMutableList(),
                    onChangeFileBlocks = {
                        viewModel.reportData.value!!.report.fileBlocks = it
                        viewModel.reportData.value!!.doneStatus = true
                        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.forEach{ savedScenario ->
                            savedScenario.reportsAssigned.forEach { savedReport ->
                                if (savedReport._id == viewModel.reportData.value!!._id){
                                    com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios[
                                        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.indexOf(
                                            savedScenario)].reportsAssigned[
                                        com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios[
                                            com.example.control_system.model.data.models.scenarioModel.SavedScenarios.scenarios.indexOf(
                                                savedScenario)].reportsAssigned.indexOf(
                                            savedReport)] =
                                        viewModel.reportData.value!!
                                }

                            }
                        }

                        LecturesServ.updateText(
                            viewModel.reportData.value!!._id,
                            it[0].text,
                            onConnectionError = {
                                onConnectionError()
                            })
                    }

                )
            }
        }
    }

}



//@Preview
//@Composable
//private fun prev(){
//    MainReportsScreen()
//}