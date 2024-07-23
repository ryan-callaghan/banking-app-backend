package com.example.banking3.User;

import java.util.List;
import java.util.stream.Collectors;
import com.example.banking3.Account.AccountDTO;

public class UserDTO {

    private Integer id;
    private String username;
    private List<AccountDTO> accounts;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setAccounts(user.getAccounts().stream().map(AccountDTO::fromEntity).collect(Collectors.toList()));
        return dto;
    }
}