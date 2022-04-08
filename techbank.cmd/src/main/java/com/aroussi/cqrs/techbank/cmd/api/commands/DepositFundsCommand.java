package com.aroussi.cqrs.techbank.cmd.api.commands;

import com.aroussi.cqrs.core.annotations.Builded;
import com.aroussi.cqrs.core.commands.BaseCommand;

@Builded
public interface DepositFundsCommand extends BaseCommand {
    Double amount();
}
