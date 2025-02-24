import java.util.Scanner;
import java.util.Random;

class Player {
    String name;
    String job;
    int health;
    int damage;
    int speed;
    int exp;
    int level;
    int expThreshold;
    int[] attackCooldown = {0, 0, 0, 0};  // Cooldown for each attack (0 means no cooldown)

    public Player(String name, String job, int health, int damage, int speed) {
        this.name = name;
        this.job = job;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.exp = 0;
        this.level = 1;
        this.expThreshold = 50;
    }

    public void attack(Enemy enemy, int attackChoice) {
        if (attackCooldown[attackChoice - 1] > 0) {  // Check if the attack is on cooldown
            System.out.println("This attack is on cooldown! Choose another one.");
            return; // Exit if the attack is on cooldown
        }

        int attackDamage = 0;
        String attackName = "";

        if (job.equals("Archer")) {
            switch (attackChoice) {
                case 1 -> { attackDamage = damage; attackName = "Arrow Shot"; }
                case 2 -> { attackDamage = damage + 5; attackName = "Power Arrow"; }
                case 3 -> { attackDamage = damage - 3; attackName = "Rapid Fire"; }
                case 4 -> { attackDamage = damage + 10; attackName = "Piercing Shot"; }
                default -> { System.out.println("Invalid attack choice!"); return; }
            }
        } else if (job.equals("Warrior")) {
            switch (attackChoice) {
                case 1 -> { attackDamage = damage; attackName = "Sword Slash"; }
                case 2 -> { attackDamage = damage + 5; attackName = "Heavy Blow"; }
                case 3 -> { attackDamage = damage - 3; attackName = "Quick Strike"; }
                case 4 -> { attackDamage = damage + 10; attackName = "Berserker Rage"; }
                default -> { System.out.println("Invalid attack choice!"); return; }
            }
        } else if (job.equals("Mage")) {
            switch (attackChoice) {
                case 1 -> { attackDamage = damage; attackName = "Magic Missile"; }
                case 2 -> { attackDamage = damage + 5; attackName = "Fireball"; }
                case 3 -> { attackDamage = damage - 3; attackName = "Ice Shard"; }
                case 4 -> { attackDamage = damage + 10; attackName = "Lightning Strike"; }
                default -> { System.out.println("Invalid attack choice!"); return; }
            }
        } else if (job.equals("Assassin")) {
            switch (attackChoice) {
                case 1 -> { attackDamage = damage; attackName = "Dagger Slash"; }
                case 2 -> { attackDamage = damage + 5; attackName = "Shadow Strike"; }
                case 3 -> { attackDamage = damage - 3; attackName = "Poison Stab"; }
                case 4 -> { attackDamage = damage + 10; attackName = "Death Blow"; }
                default -> { System.out.println("Invalid attack choice!"); return; }
            }
        }

        System.out.println(name + " uses " + attackName + " for " + attackDamage + " damage!");
        enemy.health -= attackDamage;
        System.out.println(enemy.name + " now has " + enemy.health + " health remaining.");

        // Set cooldown for all moves except the first one
        if (attackChoice != 1) {  // Set cooldown to 1 turn for all except the first attack
            attackCooldown[attackChoice - 1] = 1;
        }
    }

    public void gainExp(int amount) {
        exp += amount;
        System.out.println(name + " gained " + amount + " EXP! Total EXP: " + exp);
        if (exp >= expThreshold) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        expThreshold += 25;
        health += 10;
        damage += 3;
        speed += 2;
        System.out.println("\nCongratulations! " + name + " leveled up to Level " + level + "!");
        System.out.println("New stats: Health = " + health + ", Damage = " + damage + ", Speed = " + speed);
    }

    public void reduceCooldown() {
    for (int i = 0; i < attackCooldown.length; i++) {
        if (attackCooldown[i] > 0) {
            attackCooldown[i]--;  // Decrease cooldown by 1 each turn
            }
        }
    }

    public boolean isAlive() {
        return health > 0;
    }
}

class Enemy {
    String name;
    int health;
    int damage;
    int speed;

    public Enemy(String name, int health, int damage, int speed) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
    }

    public void attack(Player player) {
        System.out.println(name + " attacks " + player.name + " for " + damage + " damage!");
        player.health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }
}

public class RPGGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            System.out.println("Welcome to the RPG Game!");
            System.out.print("Enter your character's name: ");
            String playerName = scanner.nextLine();

            int jobChoice = 0;
            boolean validChoice = false;
            while (!validChoice) {
                try {
                    System.out.println("Choose your class:");
                    System.out.println("1. Archer\n2. Warrior\n3. Mage\n4. Assassin");
                    jobChoice = scanner.nextInt();
                    scanner.nextLine();  // consume the newline

                    if (jobChoice < 1 || jobChoice > 4) {
                        System.out.println("Invalid choice, please choose a number between 1 and 4.");
                    } else {
                        validChoice = true;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    scanner.nextLine();  // clear invalid input
                }
            }

            Player player;
            if (jobChoice == 1) {
                player = new Player(playerName, "Archer", 120, 20, 10);
            } else if (jobChoice == 2) {
                player = new Player(playerName, "Warrior", 150, 15, 6);
            } else if (jobChoice == 3) {
                player = new Player(playerName, "Mage", 110, 25, 7);
            } else {
                player = new Player(playerName, "Assassin", 115, 30, 15);
            }

            Enemy[] enemies = {
                new Enemy("Goblin", 45, 5, 5),
                new Enemy("Orc", 65, 8, 4),
                new Enemy("Troll", 70, 10, 3),
                new Enemy("Dark Knight", 100, 12, 7),
                new Enemy("Dragon (Boss)", 120, 25, 8)
            };

            for (Enemy enemy : enemies) {
                System.out.println("\nA wild " + enemy.name + " appears!");
                while (player.isAlive() && enemy.isAlive()) {
                    System.out.println("\nChoose your attack:");
                    // Display attack names based on player job
                    if (player.job.equals("Archer")) {
                        System.out.println("1. Arrow Shot\n2. Power Arrow\n3. Rapid Fire\n4. Piercing Shot");
                    } else if (player.job.equals("Warrior")) {
                        System.out.println("1. Sword Slash\n2. Heavy Blow\n3. Quick Strike\n4. Berserker Rage");
                    } else if (player.job.equals("Mage")) {
                        System.out.println("1. Magic Missile\n2. Fireball\n3. Ice Shard\n4. Lightning Strike");
                    } else if (player.job.equals("Assassin")) {
                        System.out.println("1. Dagger Slash\n2. Shadow Strike\n3. Poison Stab\n4. Death Blow");
                    }

                    int attackChoice = 0;
                    boolean validAttackChoice = false;
                    while (!validAttackChoice) {
                        try {
                            attackChoice = scanner.nextInt();
                            scanner.nextLine();  // consume the newline
                            if (attackChoice < 1 || attackChoice > 4) {
                                System.out.println("Invalid attack choice! Please choose a number between 1 and 4.");
                            } else {
                                validAttackChoice = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number between 1 and 4.");
                            scanner.nextLine();  // clear invalid input
                        }
                    }

                    player.attack(enemy, attackChoice);

                    if (enemy.isAlive()) enemy.attack(player);

                    // Reduce cooldowns after each turn
                    player.reduceCooldown();
                }

                if (!player.isAlive()) {
                    System.out.println("You were defeated. Game Over.");
                    break;
                } else {
                    System.out.println("You defeated " + enemy.name + "!");
                    player.gainExp(20);
                }
            }

            if (player.isAlive()) {
                System.out.println("Congratulations! You defeated all enemies and completed the game!");
            }

            System.out.println("\nWould you like to play again? (yes=1 / no=0): ");
            playAgain = false;
            validChoice = false;
            while (!validChoice) {
                try {
                    playAgain = scanner.nextInt() == 1;
                    scanner.nextLine();  // consume the newline
                    validChoice = true;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter 1 for yes or 0 for no.");
                    scanner.nextLine();  // clear invalid input
                }
            }
        } while (playAgain);

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
