package net.zaskamlen.itemsapi.items;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class DefaultItem extends Item implements Listener {
    public DefaultItem() {
        super(new ItemData(Material.IRON_AXE,1,1,"Тестовый предмет",
                "Тестовое описание предмета"),ItemActionType.RIGHT_CLICK);
    }

    @Override
    public void interactEntity(Player player, LivingEntity entity) {

    }

    @Override
    public void interact(Player player) {

        player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,120,5));
        player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,120,5));
        player.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,120,3));

    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (getType() != ItemActionType.ATTACK) {
            return;
        }

        if (event.getDamager() instanceof Player damager) {
            List<Entity> nearbyEntities = event.getEntity().getNearbyEntities(2, 2, 2);
            nearbyEntities.forEach(entity -> {
                if (entity instanceof LivingEntity damaged) {
                    if (!entity.equals(damager.getPlayer())) {
                        damaged.damage(10); // Может быть любое указаное число

                    }
                }
            });
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (getType() != ItemActionType.RIGHT_CLICK) {
            return;
        }

        if (event.getItem() != null) {
            ItemStack item = event.getItem();

            if (event.getAction().isRightClick()) {
                if (item.getItemMeta().getDisplayName().equals(getItemData().getName())) {
                    interact(event.getPlayer());
                }
            }
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        if (getType() != ItemActionType.RIGHT_CLICK_ENTITY) {
            return;
        }

        Entity rightClicked = event.getRightClicked();
        if (rightClicked instanceof LivingEntity livingEntity) {
            interactEntity(event.getPlayer(), livingEntity);
        }

    }

}
