package com.big_chat_brasil.bigchatbrasil.dto;
import com.big_chat_brasil.bigchatbrasil.model.planType;

import io.swagger.v3.oas.annotations.media.Schema;

public class AlterPlanTypeDTO {
    @Schema(description = "The new plan type for the client", example = "PRE_PAGO")
    private planType newPlanType;

    // Getters e Setters
    public planType getNewPlanType() {
        return newPlanType;
    }

    public void setNewPlanType(planType newPlanType) {
        this.newPlanType = newPlanType;
    }
}
