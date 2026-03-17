package br.com.joaojuniodev.spc.data.dtos.response;

import br.com.joaojuniodev.spc.models.enums.EtapaEnum;

public record StepOfCatechistResponseDTO(Long id, EtapaEnum stepEnum) {
}
