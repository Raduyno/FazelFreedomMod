package me.totalfreedom.totalfreedom.command;

import java.util.Random;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.Achievement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "I have no idea how you're still alive but here have a cake! It sucks that no one died yet :/ No fun", usage = "/<command>")
public class Command_cake extends FreedomCommand
{

    public static final String CAKE_LYRICS = "Some Good Cake!!!";
    private final Random random = new Random();

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        final StringBuilder output = new StringBuilder();

        final String[] words = CAKE_LYRICS.split(" ");
        for (final String word : words)
        {
            output.append(ChatColor.COLOR_CHAR).append(Integer.toHexString(1 + random.nextInt(14))).append(word).append(" ");
        }

        final ItemStack heldItem = new ItemStack(Material.CAKE);
        final ItemMeta heldItemMeta = heldItem.getItemMeta();
        heldItemMeta.setDisplayName((new StringBuilder()).append(ChatColor.WHITE).append("The ").append(ChatColor.DARK_GRAY).append("Lie").toString());
        heldItem.setItemMeta(heldItemMeta);

        for (final Player player : server.getOnlinePlayers())
        {
            final int firstEmpty = player.getInventory().firstEmpty();
            if (firstEmpty >= 0)
            {
                player.getInventory().setItem(firstEmpty, heldItem);
            }

            player.awardAchievement(Achievement.BAKE_CAKE);
        }

        FUtil.bcastMsg(output.toString());

        return true;
    }
}
