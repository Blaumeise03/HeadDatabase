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

/**
 * This interface represents a scrollable-menu. It gives the ability
 * of scrolling through multiple pages of {@link MenuChild MenuChilds}.
 */
public interface Scrollable {

    /**
     * Method for opening the previous page.
     * @param session the {@link MenuSession} of the player.
     */
    void goPrevious(MenuSession session);

    /**
     * Method for opening the next page.
     * @param session the {@link MenuSession} of the player.
     */
    void goNext(MenuSession session);
}
