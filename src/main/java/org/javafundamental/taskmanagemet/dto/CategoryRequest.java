package org.javafundamental.taskmanagemet.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Nama kategori tidak boleh kosong") String name,
        String color) {

}
