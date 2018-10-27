package data.controller;

import data.SensorsData;

public class SensorsHandler {

    public SensorsHandler(){}
    
    public void parseMessage(String message){
        String sensorHeader = message.substring(0,3);
        String value = message.substring(4);

        switch(sensorHeader){
            case "IR1":
                this.setIntegerData(SensorsData.TELEMETRE_AVANT_GAUCHE, value);
                break;
            case "IR2":
                this.setIntegerData(SensorsData.TELEMETRE_AVANT_DROIT, value);
                break;
            case "IR3":
                this.setIntegerData(SensorsData.TELEMETRE_ARRIERE_GAUCHE, value);
                break;
            case "IR4":
                this.setIntegerData(SensorsData.TELEMETRE_ARRIERE_DROIT, value);
                break;
            case "IR5":
                this.setIntegerData(SensorsData.TELEMETRE_COTE_GAUCHE, value);
                break;
            case "IR6":
                this.setIntegerData(SensorsData.TELEMETRE_COTE_DROIT, value);
                break;
        }
    }

    private void setIntegerData(SensorsData sensorData, String value){
        sensorData.setData(Integer.parseInt(value));
    }
}
