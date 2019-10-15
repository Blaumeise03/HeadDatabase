/*
 *     Copyright (C) 2019  Blaumeise03 - bluegame61@gmail.com
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published
 *     by the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.blaumeise03.headDatabase.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ScrollableSubMenu extends ScrollableMenu implements MenuChild {
    private List<MenuChild> children;
    private int index;
    private String name = null;
    private ItemStack icon = null;

    public ScrollableSubMenu(List<MenuChild> content, String name, ItemStack icon, int pageSize) {
        super(content, pageSize);
        this.name = name;
        this.icon = icon;
    }

    @Override
    public void onClick(Player p, MenuSession session) {
        session.goTo(this);
    }

    @Override
    public ItemStack render() {
        ItemStack render;
        if(icon == null) render = new ItemStack(Material.ZOMBIE_HEAD);
        else render = icon;
        ItemMeta iMeta = render.getItemMeta();
        assert iMeta != null;
        iMeta.setDisplayName("§a" + name);
        render.setItemMeta(iMeta);
        return render;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getName() {
        return name;
    }
}
