package org.javafundamental.taskmanagemet.dto;

import org.javafundamental.taskmanagemet.entity.TaskStatus;

import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest(
        @NotNull(message = "Status tidak boleh kosong") TaskStatus status) {

}
