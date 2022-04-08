package com.aroussi.cqrs.techbank.cmd.api.commands;

import com.aroussi.cqrs.core.annotations.Builded;
import com.aroussi.cqrs.core.commands.BaseCommand;
import com.aroussi.cqrs.techbank.common.dtos.AccountType;

@Builded
public interface OpenAccountCommand extends BaseCommand {
    Double balance();
    String accountHolder();
    AccountType type();
}
