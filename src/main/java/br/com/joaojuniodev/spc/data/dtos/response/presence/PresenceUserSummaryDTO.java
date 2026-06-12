package br.com.joaojuniodev.spc.data.dtos.response.presence;

public record PresenceUserSummaryDTO(
    String username,
    String fullName,
    Long massId,
    Long totalCatechumens
) {}