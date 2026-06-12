package br.com.joaojuniodev.spc.data.dtos.response.presence;

import java.time.LocalDateTime;

public record PresenceUserSummaryDTO(
    String username,
    String fullName,
    Long massId,
    Long totalCatechumens,
    LocalDateTime registeredAt
) {}