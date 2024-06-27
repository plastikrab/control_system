package com.example.control_system.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.control_system.data.ScenariosSaver
import com.example.control_system.data.model.scenarioModel.ReportAssignment
import com.example.control_system.data.model.scenarioModel.SavedScenarios
import com.example.control_system.data.model.scenarioModel.Scenario
import com.example.control_system.data.model.scenarioModel.ScenarioData
import com.example.control_system.data.model.scenarioModel.UpdateChoiceReport
import com.example.control_system.data.model.scenarioModel.UpdateChoiceReports
import com.example.control_system.network.LecturesServer
import com.example.control_system.ui.components.CheckboxReport
import com.example.control_system.ui.components.RadioButtonReport
import com.example.control_system.ui.components.TaskCard
import com.example.control_system.ui.components.TextBlock
import com.example.control_system.ui.theme.BordersColour
import com.example.control_system.ui.theme.TextBlack


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainReportsScreen(
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

    val repTemp : ReportAssignment? = null
    var reportData by remember { mutableStateOf(repTemp) }
    val savedScenariosList: MutableList<Scenario> = mutableListOf()


    LazyColumn(
    ) {
        Log.d("MyLog","MainScreen open")
        items(scenarioList.scenarios){scenario ->
            TaskCard(
                scenario,
                openBottomSheet = {
                    reportData = it
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


    if (showBottomSheet && reportData != null) {
        ModalBottomSheet(
            modifier = Modifier
                .size(726.dp),
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        ) {
            Text(
                text = reportData?.report?.title ?: "No Data",
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
                text = reportData?.report?.description ?: "No Data",
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
            if (reportData?.report?.type == "choiceReports"){
                val choiceList = mutableListOf<UpdateChoiceReport>()


                Log.d("MyLog", "RadioButton opening")
                RadioButtonReport(
                    report = reportData!!,
                    selected = {
                        Log.d("MyLog", "Clicked")
                        choiceList.clear()
                        var i = 0
                        reportData!!.report.choiceReports.forEach{item ->
                            item.answer = item.text == it
                            reportData!!.doneStatus = true

                            i++
                            choiceList.add(
                                UpdateChoiceReport(
                                    reportData!!.choiceReportsAssigned!![i-1]._id,
                                    item.answer
                                )
                            )
                        }
                        reportData!!.report.choiceReports.forEach{item ->
                            Log.d("MyLog", item.text + " " + item.answer)
                        }
                        reportData!!.doneStatus = true
                        SavedScenarios.scenarios.forEach{savedScenario ->
                            savedScenario.reportsAssigned.forEach { savedReport ->
                                if (savedReport._id == reportData!!._id){
                                    SavedScenarios.scenarios[
                                        SavedScenarios.scenarios.indexOf(
                                            savedScenario)].reportsAssigned[
                                        SavedScenarios.scenarios[
                                            SavedScenarios.scenarios.indexOf(
                                                savedScenario)].reportsAssigned.indexOf(
                                            savedReport)] =
                                        reportData!!
                                }

                            }
                        }
                        LecturesServer.updateChoiceReports(
                            UpdateChoiceReports(
                                reportData!!._id,
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
            if (reportData?.report?.type == "choiceReportsMany"){
                val choiceList = mutableListOf<UpdateChoiceReport>()


                Log.d("MyLog", "CheckBox opening")
                CheckboxReport(
                    report = reportData!!,
                    onChange = {
                        choiceList.clear()
                        var i = 0
                        reportData!!.report.choiceReports.forEach{item ->
                            item.answer = it[i]
                            i++
                            choiceList.add(
                                UpdateChoiceReport(
                                    reportData!!.choiceReportsAssigned!![i-1]._id,
                                    item.answer
                                )
                            )
                        }
                        reportData!!.report.choiceReports.forEach{item ->
                            Log.d("MyLog", item.text + " " + item.answer)
                        }

                        reportData!!.doneStatus = true
                        SavedScenarios.scenarios.forEach{savedScenario ->
                            savedScenario.reportsAssigned.forEach { savedReport ->
                                if (savedReport._id == reportData!!._id){
                                    SavedScenarios.scenarios[
                                        SavedScenarios.scenarios.indexOf(
                                            savedScenario)].reportsAssigned[
                                        SavedScenarios.scenarios[
                                            SavedScenarios.scenarios.indexOf(
                                                savedScenario)].reportsAssigned.indexOf(
                                            savedReport)] =
                                        reportData!!
                                }

                            }
                        }
                        ScenariosSaver.updateScenarios(SavedScenarios.scenarios, reportData!!)
                        LecturesServer.updateChoiceReports(
                            UpdateChoiceReports(
                                reportData!!._id,
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
            if (reportData?.report?.type == "text"){
                TextBlock(
                    blocks = reportData!!.report.fileBlocks.toMutableList(),
                    onChangeFileBlocks = {
                        reportData!!.report.fileBlocks = it
                        reportData!!.doneStatus = true
                        SavedScenarios.scenarios.forEach{savedScenario ->
                            savedScenario.reportsAssigned.forEach { savedReport ->
                                if (savedReport._id == reportData!!._id){
                                    SavedScenarios.scenarios[
                                        SavedScenarios.scenarios.indexOf(
                                            savedScenario)].reportsAssigned[
                                        SavedScenarios.scenarios[
                                            SavedScenarios.scenarios.indexOf(
                                                savedScenario)].reportsAssigned.indexOf(
                                            savedReport)] =
                                        reportData!!
                                }

                            }
                        }

                        LecturesServer.updateText(
                            reportData!!._id,
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