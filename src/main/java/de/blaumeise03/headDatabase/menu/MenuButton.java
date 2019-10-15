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

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * This class represents a button inside the menu. If
 * clicked it will execute the {@link #onClick(Player, MenuSession)}-
 * method.
 */
public abstract class MenuButton implements MenuChild{
    private ItemStack icon;
    private String name;
    private int index;

    /**
     * Constructor for the menu.
     * @param icon the icon of the button (including the display-name).
     * @param name the name of the button.
     * @param index the index of the button (will be overwritten by the
     *              {@link Menu#rearrange()}-method.
     */
    public MenuButton(ItemStack icon, String name, int index) {
        this.icon = icon;
        this.name = name;
        this.index = index;
    }

    /**
     * Constructor for the menu.
     * @param icon the icon of the button (including the display-name).
     * @param name the name of the button.
     */
    public MenuButton(ItemStack icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    /**
     * This method defines what happens if the player clicks on it. It will
     * be called by the {@link Menu#processClick(Player, int, MenuSession)}-
     * method.
     * @param p the player who clicked.
     * @param session the {@link MenuSession} of the player.
     */
    @Override
    public abstract void onClick(Player p, MenuSession session);

    /**
     * Returns the <code>ItemStack</code> wich will be displayed.
     * @return the <code>ItemStack</code>-icon.
     */
    @Override
    public ItemStack render() {
        return icon;
    }

    /**
     * Getter for the index.
     * @return the index of the button.
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * Setter for the index.
     * @param index the new index.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Getter for the name.
     * @return the name of the button.
     */
    @Override
    public String getName() {
        return name;
    }
}
