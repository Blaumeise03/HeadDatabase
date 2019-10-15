# HeadDatabase
Ein Spigot/Paper-Plugin welches eine Kopf-Datenbank hinzufügt. Es verfügt über eine grafische Datenbank sowohl die möglichkeit sich Köpfe einfach per Befehl zu geben.

Befehle:
 - /head : Öffnet die grafische Datenbank
 - /head LINK <Link> : Gibt dir den Kopf vom angegebenen Link. Beispiel: "http://textures.minecraft.net/texture/2a3b8f681daad8bf436cae8da3fe8131f62a162ab81af639c3e0644aa6abac2f" ODER nur "2a3b8f681daad8bf436cae8da3fe8131f62a162ab81af639c3e0644aa6abac2f"
 - /head PLAYER <Spielername> : Gibt dir den Kopf des angegebenen Spielers.
 - /head reload : Lädt die Datenbank neu.
 - /head preventSave : Deaktiviert das Speichern der Datenbank beim nächsten Neustart / Reload. (wenn ein zweites mal eingegeben wird das Speichern wieder aktiviert). So werden in der Datei vorgenommene Veränderungen nicht überschrieben (wenn z.B.: neue Köpfe eingefügt wurden).

Rechte:
 - head.head : Berechtigung für den /head Befehl (und alle unterbefehle)
 - head.* : Alle Berechtigungen.
