# KeyAll Plugin
 
The KeyAll plugin automates the distribution of keys to players at regular intervals.
 
## Features
 
- Scheduled execution of a command to give keys to all players.
- PlaceholderAPI integration for displaying countdown timer.
 
## Dependencies
 
Make sure you have the following plugin installed:
 
- **ExcellentCrates**: This plugin is ESSENTIAL. KeyAll  will not work without ExcellentCrates.
- **PlaceholderAPI**: This plugin is required for displaying dynamic information, such as the countdown timer.
 
## Installation
 
1. Download the latest release from the [Releases](https://www.spigotmc.org/resources/keyall-1-17x-1-20x.113711/) page.
2. Place the `KeyAll.jar` file into the `plugins` folder of your Bukkit/Spigot server.
3. Restart your server to enable the plugin.
 
## Configuration
 
The plugin reads its configuration from the `config.yml` file. You can customize the following settings:
 
- `timerInterval`: The interval between key distributions in seconds.
- `keyName`: The name of the key to be given.
- `keyAmount`: The number of keys to be given to each player.
 
```yaml
# KeyAll NeonWave Development
# This plugin only works with ExcellentCrates

# The Interval for KeyAll events (In seconds)
timerInterval: 60

# Name of the key
keyName: Emerald

# The amount of keys
keyAmount: 1
```
 
## Placeholders
 
The plugin provides a PlaceholderAPI expansion for displaying the countdown timer. Use the following placeholder:
 
- `%keyall_timer%`: Displays the remaining time until the next key distribution.
 
## Commands
 
- `/keyall reload`: Reloads the plugin configuration.
 
## Usage
 
Once the plugin is installed, it will automatically start distributing keys at the specified intervals. Players can use the provided PlaceholderAPI placeholder to see the countdown timer.
 
## Permissions
 
- `keyall.reload`: Allows players to use the `/keyall reload` command.
 
 
## Issues
 
If you encounter any issues or have suggestions, please [create a new issue](https://github.com/Ammar-XD/KeyAll/issues).
 
## License
 
This plugin is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
 
