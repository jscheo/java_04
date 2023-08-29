package main;

import service.BankService;

import java.util.Scanner;

public class BankMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService();

        boolean run = true;
        while(run){
            System.out.println("-------------------------------------------------------------------");
            System.out.println("1.계좌등록 2.잔액조회 3.입금 4.출금 5.입출금 내역조회 0.종료");
            System.out.println("-------------------------------------------------------------------");
            System.out.print("메뉴선택:");
            int sel = scanner.nextInt();

            if(sel == 1){
                System.out.println("계좌등록");
                bankService.save();
            }else if(sel == 2){
                System.out.println("잔액조회");
                bankService.search();
            }else if(sel == 3){
                System.out.println("입금");
                bankService.inputMoney();
            }else if(sel == 4){
                System.out.println("출금");
                bankService.outMoney();
            }else if(sel == 5){
                System.out.println("입출금 내역조회");
                bankService.findByAc();
            }else if(sel == 0){
                System.out.println("프로그램 종료");
                run = false;
            }else{
                System.out.println("다시 선택하세요");
            }
        }

    }
}
