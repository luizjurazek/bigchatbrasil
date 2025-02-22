package com.big_chat_brasil.bigchatbrasil.dto;
import com.big_chat_brasil.bigchatbrasil.model.planType;

public class AlterPlanTypeDTO {
  private planType newPlanType;

    // Getters e Setters
    public planType getNewPlanType() {
        return newPlanType;
    }

    public void setNewPlanType(planType newPlanType) {
        this.newPlanType = newPlanType;
    }
}
