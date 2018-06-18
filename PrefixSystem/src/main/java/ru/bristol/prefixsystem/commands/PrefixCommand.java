package ru.bristol.prefixsystem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.bristol.prefixsystem.files.config.ConfigRepository;
import ru.bristol.prefixsystem.files.lang.LangRepository;
import ru.bristol.prefixsystem.managers.VaultManager;
import ru.bristol.prefixsystem.utils.ColorUtil;
import ru.bristol.prefixsystem.utils.MessageUtil;
import ru.bristol.prefixsystem.utils.StringUtil;

public class PrefixCommand implements CommandExecutor {

    private LangRepository langRepository;
    private ConfigRepository configRepository;
    private VaultManager vaultManager;

    public PrefixCommand(LangRepository langRepository, ConfigRepository configRepository,  VaultManager vaultManager) {
        this.langRepository = langRepository;
        this.configRepository = configRepository;
        this.vaultManager = vaultManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            MessageUtil.sendMessage(sender, langRepository.getMessage("main.only-for-players"));
            return true;
        }
        if(!sender.hasPermission("prefixsystem.prefix")) {
            MessageUtil.sendMessage(sender, langRepository.getMessage("main.no-perm"));
            return true;
        }
        if(args.length == 0) {
            MessageUtil.sendMessage(sender, langRepository.getMessage("prefix.usage"));
            return true;
        }
        String prefix = StringUtil.build(args, 0);
        if(configRepository.isBlockedPrefix(prefix) && !sender.hasPermission("prefixsystem.unblocked")) {
            MessageUtil.sendMessage(sender, langRepository.getMessage("prefix.blocked"));
            return true;
        }
        prefix = ColorUtil.color(prefix);
        vaultManager.setPrefix(sender.getName(), prefix);
        MessageUtil.sendMessage(sender, langRepository.getMessage("prefix.success"));
        return true;
    }

}
