# **Rambosho - Plugin Minecraft**

Un plugin simple pour jouer au célèbre jeu "Pierre, Papier, Ciseaux" directement dans Minecraft !

---

## **Fonctionnalités**
- **Multijoueur** : Défiez vos amis dans une partie 1v1.
- **Interface intuitive** : Choisissez votre action (Pierre, Papier, ou Ciseaux) via une interface graphique (GUI).
- **Scores en temps réel** : Affichage des scores dans le titre de l'inventaire sous la forme :
  - `Nom de l'adversaire, Score de l'adversaire - Votre score, You`
- **Effets sonores** :
  - Confirmation de choix.
  - Sons de victoire, défaite, ou égalité à la fin de chaque round.

---

## **Installation**
1. Téléchargez le fichier `.jar` du plugin.
2. Déplacez-le dans le dossier `plugins` de votre serveur Minecraft.
3. Redémarrez le serveur.

---

## **Commandes**
| Commande              | Description                           | Permissions           |
|-----------------------|---------------------------------------|-----------------------|
| `/ppc <joueur>` | Invitez un joueur à une partie        | Aucun                 |
| `/ppc accept <joueur>` | Acceptez une invitation de partie     | Aucun                 |

---

## **Comment jouer ?**
1. **Invitez un joueur** : Tapez `/ppc <joueur>` pour inviter un autre joueur connecté.
2. **Acceptez l'invitation** : Le joueur invité doit utiliser `/ppc accept <joueur>` pour commencer la partie.
3. **Choisissez votre action** :
   - Une interface GUI s'ouvre avec trois choix :
     - **Pierre** : Icône de pierre.
     - **Papier** : Icône de papier.
     - **Ciseaux** : Icône de ciseaux.
   - Cliquez sur l'un des items pour faire votre choix.
4. **Résultat** :
   - Les scores sont affichés en temps réel dans le titre de l'inventaire.
   - Des messages et des sons indiquent si vous avez gagné, perdu, ou si c'est une égalité.

---

## **Configuration**
Aucune configuration supplémentaire n'est nécessaire. Le plugin fonctionne dès l'installation. 😊

---

## **Exemples de messages**
- **Début de partie** :
  - "§aVous avez accepté l'invitation de JohnDoe !"
  - "§aJohnDoe a accepté votre invitation !"
- **Résultat d'un round** :
  - Victoire : "§aVous avez gagné ce round !"
  - Égalité : "§eÉgalité pour ce round !"
  - Défaite : "§cVous avez perdu ce round…"

---

## **Développement**
### **Classes principales :**
- **`PPCGUIManager`** :
  - Gestion des GUI et des scores.
- **`PPCEventListener`** :
  - Écoute des clics dans l'inventaire et résolution des choix.
- **`PPCAcceptCommand`** :
  - Commande pour accepter une invitation.
- **`PPCCommand`** :
  - Commande pour inviter un joueur.

---

## **Compatibilité**
- **Versions Minecraft** : 1.21.3.
- **Dépendances** : Aucune.

---

## **Crédits**
Créé par **ChairGamerTag87** ! 💙

