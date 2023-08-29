package repository;

import dto.AccountDTO;
import dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public class BankRepository {
    List<ClientDTO> clientList = new ArrayList<>();
    List<AccountDTO> bankingList = new ArrayList<>();

    //계좌 등록 시 중복 체크 매서드
    public boolean cheak(String account) {
        for (ClientDTO clientDTO : clientList) {
            if (account.equals(clientDTO.getAccountNumber())) {
                return false;
            }
        }
        return true;
    }
    // 입금, 출금, 입출금 내역에서 계좌 확인용 매서드
    public boolean cheak1(String account) {
        for (ClientDTO clientDTO : clientList) {
            if (account.equals(clientDTO.getAccountNumber())) {
                return true;
            }
        }
        return false;
    }
    // 계좌 등록 매서드
    public boolean save(ClientDTO clientDTO) {
        return clientList.add(clientDTO);
    }

    // 잔액조회때 맞는 계좌를 찾아오기 위한 매서드
    public ClientDTO search(String account) {
        for (ClientDTO clientDTO : clientList) {
            if (account.equals(clientDTO.getAccountNumber())) {
                return clientDTO;
            }
        }
        return null;
    }
    // 입금 시 계좌에 입금 금액을 셋해주기위한 매서드
    public ClientDTO inputMoney(String inAccount, int inMoney) {
        for (ClientDTO clientDTO : clientList) {
            if (inAccount.equals(clientDTO.getAccountNumber())) {
                clientDTO.setBalance(clientDTO.getBalance() + inMoney);
                return clientDTO;
            }
        }
        return null;
    }
    // 출금 시 비밀번호 확인을 위한 매서드
    public boolean passCheak(String pass) {
        for (ClientDTO clientDTO : clientList) {
            if (pass.equals(clientDTO.getClientPass())) {
                return true;
            }
        }
        return false;
    }
    // 출금 시 사용하는 매서드
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
    //입금할 때 Accoutlsit 에 추가해주는 매서드
    public boolean inputList(AccountDTO accountDTO) {
        return bankingList.add(accountDTO);
    }
    // 출금 때 리스트에 추가
    public boolean outputList(AccountDTO accountDTO) {
        return bankingList.add(accountDTO);
    }
    // 입출금 전체 내역 출력 매서드
    public List<AccountDTO> allList() {
        return bankingList;
    }
    // 입출력 리스트 중 입금리스트
    public List<AccountDTO> inList() {
        List<AccountDTO> inputList = new ArrayList<>();
        for (AccountDTO accountDTO : bankingList) {
            if (accountDTO.getWithdraw() == 0) {
                inputList.add(accountDTO);
            }
        }
        return inputList;
    }
    // 출금 리스트
    public List<AccountDTO> outList() {
        List<AccountDTO> outputList = new ArrayList<>();
        for (AccountDTO accountDTO : bankingList) {
            if (accountDTO.getWithdraw() == 0) {
                outputList.add(accountDTO);
            }
        }
        return outputList;
    }
}
