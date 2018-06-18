package ru.bristol.prefixsystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.bristol.prefixsystem.files.config.ConfigRepository;
import ru.bristol.prefixsystem.files.lang.LangRepository;
import ru.bristol.prefixsystem.managers.VaultManager;
import ru.bristol.prefixsystem.utils.ColorUtil;
import ru.bristol.prefixsystem.utils.MessageUtil;
import ru.bristol.prefixsystem.utils.StringUtil;

public class AdminPrefixCommand implements CommandExecutor {

    private LangRepository langRepository;
    private ConfigRepository configRepository;
    private VaultManager vaultManager;

    public AdminPrefixCommand(LangRepository langRepository, ConfigRepository configRepository, VaultManager vaultManager) {
        this.configRepository = configRepository;
        this.langRepository = langRepository;
        this.vaultManager = vaultManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!sender.hasPermission("prefixsystem.aprefix")) {
            MessageUtil.sendMessage(sender, langRepository.getMessage("main.no-perm"));
            return true;
        }
        if(args.length <= 1) {
            MessageUtil.sendMessage(sender, langRepository.getMessage("aprefix.usage"));
            return true;
        }
        if(args[0].equals(sender.getName())) {
            MessageUtil.sendMessage(sender, langRepository.getMessage("aprefix.not-yourself"));
            return true;
        }
        String prefix = StringUtil.build(args, 1);
        if(configRepository.isBlockedPrefix(prefix) && !sender.hasPermission("prefixsystem.unblocked")) {
            MessageUtil.sendMessage(sender, langRepository.getMessage("aprefix.blocked"));
            return true;
        }
        prefix = ColorUtil.color(prefix);
        vaultManager.setPrefix(args[0], prefix);
        MessageUtil.sendMessage(sender, langRepository.getMessage("aprefix.success"));
        Bukkit.getOnlinePlayers().stream()
                .filter(pl -> pl.getName().equals(args[0]))
                .forEach(pl -> MessageUtil.sendMessage(pl, langRepository.getMessage("aprefix.success-for-player")));
        return true;
    }

}
