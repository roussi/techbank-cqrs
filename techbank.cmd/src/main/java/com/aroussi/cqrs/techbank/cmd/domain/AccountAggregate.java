package com.aroussi.cqrs.techbank.cmd.domain;

import com.aroussi.cqrs.core.domain.AggregateRoot;
import com.aroussi.cqrs.core.events.BaseEvent;
import com.aroussi.cqrs.techbank.cmd.api.commands.CloseAccountCommand;
import com.aroussi.cqrs.techbank.cmd.api.commands.DepositFundsCommand;
import com.aroussi.cqrs.techbank.cmd.api.commands.OpenAccountCommand;
import com.aroussi.cqrs.techbank.cmd.api.commands.WithdrawFundsCommand;
import com.aroussi.cqrs.techbank.common.events.AccountClosedEvent;
import com.aroussi.cqrs.techbank.common.events.AccountOpenedEvent;
import com.aroussi.cqrs.techbank.common.events.FundsDepositedEvent;
import com.aroussi.cqrs.techbank.common.events.FundsWithdrawnEvent;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand openAccountCommand) {
        raiseEvent(AccountOpenedEvent.builder()
                .id(openAccountCommand.id())
                .accountHolder(openAccountCommand.accountHolder())
                .balance(openAccountCommand.balance())
                .openingDate(ZonedDateTime.now())
                .type(openAccountCommand.type())
                .build());
    }

    void apply(AccountOpenedEvent event) {
        this.id = event.id();
        this.balance = event.balance();
        this.active = true;
    }

    public void depositFunds(DepositFundsCommand depositFundsCommand) {
        if(!active) {
            throw new IllegalStateException("Account must be active in order to deposit funds on it");
        }
        if(depositFundsCommand.amount() <= 0) {
            throw new IllegalArgumentException("The deposited funds must be greater than 0");
        }

        raiseEvent(FundsDepositedEvent.builder()
                .id(depositFundsCommand.id())
                .amount(depositFundsCommand.amount())
                .build());
    }

    public void apply(FundsDepositedEvent fundsDepositedEvent) {
        this.id = fundsDepositedEvent.id();
        this.balance+= fundsDepositedEvent.amount();
    }

    public void withdrawFunds(WithdrawFundsCommand withdrawFundsCommand) {
        if(!active) {
            throw new IllegalStateException("Account must be active in order to withdraw funds on it");
        }
        if(withdrawFundsCommand.amount() <= 0) {
            throw new IllegalArgumentException("The withdrawn funds must be greater than 0");
        }
        if(withdrawFundsCommand.amount() > balance) {
            throw new IllegalStateException("The requested amount is greater than the actual balance");
        }

        raiseEvent(FundsWithdrawnEvent.builder()
                .id(withdrawFundsCommand.id())
                .amount(withdrawFundsCommand.amount())
                .build());
    }

    public void apply(FundsWithdrawnEvent fundsWithdrawnEvent) {
        this.id = fundsWithdrawnEvent.id();
        this.balance-= fundsWithdrawnEvent.amount();
    }

    public void closeAccount(CloseAccountCommand closeAccountCommand) {
        if(!active) {
            throw new IllegalStateException("Account is already closed");
        }

        raiseEvent(AccountClosedEvent.builder()
                .id(closeAccountCommand.id())
                .build());
    }

    public void apply(AccountClosedEvent accountClosedEvent) {
        this.id = accountClosedEvent.id();
        this.active = false;
    }
}
