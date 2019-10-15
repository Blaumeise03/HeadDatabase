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
 * This interface represents all contents of any {@link Menu menu}.
 */
public interface MenuChild {

    /**
     * This method will be called if the player clicks on
     * the item. It defines what should happen. (e.g.
     * a new {@link SubMenu SubMenu} will be opened.
     * @param p the player who clicked.
     * @param session the {@link MenuSession} of the player.
     */
    void onClick(Player p, MenuSession session);

    /**
     * Getter for the <code>ItemStack</code>-icon
     * @return the icon.
     */
    ItemStack render();

    /**
     * Getter for the index.
     * @return the index.
     */
    int getIndex();

    /**
     * Setter for the index.
     * @param index the new index.
     */
    void setIndex(int index);

    /**
     * Getter for the name.
     * @return the name.
     */
    String getName();
}
