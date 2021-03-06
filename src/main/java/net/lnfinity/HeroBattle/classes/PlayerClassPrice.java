package net.lnfinity.HeroBattle.classes;

/*
 * This file is part of HeroBattle.
 *
 * HeroBattle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * HeroBattle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with HeroBattle.  If not, see <http://www.gnu.org/licenses/>.
 */
public enum PlayerClassPrice
{
	/**
	 * A free class.
	 */
	FREE,

	/**
	 * A class buyable in the in-game shop (with in-game currency).
	 */
	PAID,

	/**
	 * An easter-egg class. Not available through usual interfaces, they are
	 * hidden somewhere in the game...
	 */
	EASTER_EGG
}
