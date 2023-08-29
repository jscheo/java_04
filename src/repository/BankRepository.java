package repository;

import dto.AccountDTO;
import dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public class BankRepository {
    List<ClientDTO> clientList = new ArrayList<>();
    List<AccountDTO> bankingList = new ArrayList<>();

    public boolean cheak(String account) {
        for (ClientDTO clientDTO : clientList) {
            if (account.equals(clientDTO.getAccountNumber())) {
                return false;
            }
        }
        return true;
    }

    public boolean cheak1(String account) {
        for (ClientDTO clientDTO : clientList) {
            if (account.equals(clientDTO.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }

    public boolean save(ClientDTO clientDTO) {
        return clientList.add(clientDTO);
    }

    public ClientDTO search(String account) {
        for (ClientDTO clientDTO : clientList) {
            if (account.equals(clientDTO.getAccountNumber())) {
                return clientDTO;
            }
        }
        return null;
    }

    public ClientDTO inputMoney(String inAccount, int inMoney) {
        for (ClientDTO clientDTO : clientList) {
            if (inAccount.equals(clientDTO.getAccountNumber())) {
                clientDTO.setBalance(clientDTO.getBalance() + inMoney);
                return clientDTO;
            }
        }
        return null;
    }

    public boolean passCheak(String pass) {
        for (ClientDTO clientDTO : clientList) {
            if (pass.equals(clientDTO.getClientPass())) {
                return true;
            }
        }
        return false;
    }

    public ClientDTO outMoney(String outAccount, int outMoney) {
        for (ClientDTO clientDTO : clientList) {
            if (outAccount.equals(clientDTO.getAccountNumber())) {
                if (outMoney <= clientDTO.getBalance()) {
                    clientDTO.setBalance(clientDTO.getBalance() - outMoney);
                    return clientDTO;
                }
            }
        }
        return null;
    }

    public boolean inpuList(AccountDTO accountDTO) {
        return bankingList.add(accountDTO);
    }


    public List<AccountDTO> list() {
        return bankingList;
    }

    public boolean outList(AccountDTO accountDTO) {
        return bankingList.add(accountDTO);
    }
}
