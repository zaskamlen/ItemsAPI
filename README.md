
![Logo](https://media.discordapp.net/attachments/1060479151389282354/1247495745464963072/image.png?ex=66603c73&is=665eeaf3&hm=bba58ee5595d8470df24f5e2259e8fee75bf92500ebf653f40f56ad50befdb15&=&format=webp&quality=lossless)


# ItemsAPI 

Данное API помогает создавать свои предметы

Эвенты создания




## Создание и импорт предмета

```java
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

```

```java
public final class ItemsAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new DefaultItem(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
```


## Какие бывают ItemActionType

`ATTACK` При аттаке по существу

`RIGHT_CLICK_ENTITY` При нажатии на существо

`RIGHT_CLICK` При нажатии правой кнопки мыши

`INVENTORY` При держании предмета в инвенторе

