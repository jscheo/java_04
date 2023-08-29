package service;

import dto.AccountDTO;
import dto.ClientDTO;
import repository.BankRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BankService {
    BankRepository bankRepository = new BankRepository();
    Scanner scanner = new Scanner(System.in);
    public void save() {
        LocalDateTime now = LocalDateTime.now();
        boolean run = true;
        while(run){
            System.out.print("희망하는 계좌번호 :");
            String account = scanner.next();
            boolean result = bankRepository.cheak(account);
            if(result){
                System.out.print("이름 :");
                String name = scanner.next();
                System.out.print("비밀번호: ");
                String pass = scanner.next();
                String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                ClientDTO clientDTO = new ClientDTO(name, account, pass, date);

                boolean result1 = bankRepository.save(clientDTO);
                if(result1){
                    System.out.println("계좌등록 완료");
                    System.out.println(clientDTO);
                    run = false;
                }else{
                    System.out.println("등록 실패");
                }

            }else{
                System.out.println("계좌번호가 중복되었습니다.");
            }
        }
    }

    public void search() {
        System.out.print("계좌번호 :");
        String account = scanner.next();
        ClientDTO clientDTO = bankRepository.search(account);

        if(clientDTO != null){
            System.out.println("현재잔액: " + clientDTO.getBalance());
        }else{
            System.out.println("계좌가 존재하지 않습니다.");
        }
    }

    public void inputMoney() {
        LocalDateTime now = LocalDateTime.now();
        System.out.print("입금할 계좌번호:");
        String inAccount = scanner.next();
        boolean result = bankRepository.cheak1(inAccount);
        if(result){
            System.out.print("입금할 금액: ");
            int inMoney = scanner.nextInt();
            String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ClientDTO clientDTO = bankRepository.inputMoney(inAccount, inMoney);
            System.out.println(clientDTO);
            AccountDTO accountDTO = new AccountDTO(inAccount, inMoney, 0, date);
            boolean result1 = bankRepository.inpuList(accountDTO);
            if(result1){
                System.out.println(result1);
            }
        }else{
            System.out.println("계좌가 존재하지 않습니다.");
        }

    }

    public void outMoney() {
        LocalDateTime now = LocalDateTime.now();
        System.out.print("출금하는 계좌: ");
        String outAccount = scanner.next();
        boolean result = bankRepository.cheak1(outAccount);
        if(result){
            System.out.print("계좌비밀번호: ");
            String pass = scanner.next();
            boolean result1 = bankRepository.passCheak(pass);
            if(result1){
                System.out.print("출금할 금액: ");
                int outMoney = scanner.nextInt();
                ClientDTO clientDTO = bankRepository.outMoney(outAccount, outMoney);
                if(clientDTO != null){
                    System.out.println("출금이 완료되었습니다.");
                    String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    AccountDTO accountDTO = new AccountDTO(outAccount, 0, outMoney, date);
                    boolean result2 = bankRepository.outList(accountDTO);
                    System.out.println(accountDTO);
                }else{
                    System.out.println("잔액이 부족합니다.");
                }
            }else{
                System.out.println("비밀번호가 틀립니다.");
            }
        }else{
            System.out.println("계좌가 없습니다.");
        }
    }

    public void findByAc() {
        System.out.println("계좌번호 : ");
        String account = scanner.next();
        boolean result = bankRepository.cheak1(account);
        if(result){
            boolean run = true;
            while(run){
                System.out.println("-------------------------------------");
                System.out.println("1.전체내역 2.입금내역 3.출금내역 4.종료");
                System.out.println("-------------------------------------");
                int sel = scanner.nextInt();

                if(sel == 1){
                    System.out.println("전체내역");
                    List<AccountDTO> accountDTOList = bankRepository.list();
                    System.out.println(accountDTOList);
                }else if(sel == 2){
                    System.out.println("입금내역");

                }else if(sel == 3){
                    System.out.println("출금내역");
                }else if(sel == 4){
                    System.out.println("종료");
                    run = false;
                }else{
                    System.out.println("다시선택하세요");
                }
            }
        }else{
            System.out.println("계좌가 존재하지 않습니다.");
        }
    }
}
