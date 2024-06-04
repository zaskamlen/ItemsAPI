package net.zaskamlen.itemsapi.items;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public abstract class Item {


    private final ItemActionType type;
    private final ItemStack stack;
    private final ItemData itemData;


    protected Item(ItemData itemData, ItemActionType type) {
        this.type = type;
        this.itemData = itemData;
        this.stack = item();
    }

    public abstract void interactEntity(Player player, LivingEntity entity);
    public abstract void interact(Player player);

    private ItemStack item() {
        ItemStack itemStack = new ItemStack(itemData.getMaterial(), itemData.getAmount());

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(itemData.getName());
        itemMeta.setLore(Arrays.asList(itemData.getLore()));
        itemMeta.setCustomModelData(itemData.getCustomModelData());

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }


    public ItemStack getStack() {
        return stack;
    }

    public ItemData getItemData() {
        return itemData;
    }

    public ItemActionType getType() {
        return type;
    }

    public void give(Player player) {
        player.getInventory().addItem(stack);
    }

}
