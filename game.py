import pygame
import sys
import random

# Initialize Pygame
pygame.init()

# Set up the screen dimensions
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600

# Define colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)

# Create the game window
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption('Enhanced Pygame Game')

# Load and scale the character image
character = pygame.image.load('image.jpg')
character = pygame.transform.scale(character, (50, 50))
background = pygame.image.load('background.jpeg')
background = pygame.transform.scale(background, (SCREEN_WIDTH, SCREEN_HEIGHT))

# Load, scale, and position the collectible item randomly on the screen
collectible = pygame.image.load('collectible.jpg')   
collectible = pygame.transform.scale(collectible, (30, 30))
collectible_rect = collectible.get_rect()
collectible_rect.topleft = (random.randint(0, SCREEN_WIDTH - 30), random.randint(0, SCREEN_HEIGHT - 30))

# Position the character in the center of the screen
character_rect = character.get_rect()
character_rect.topleft = (SCREEN_WIDTH // 2, SCREEN_HEIGHT // 2)
speed = 5

# Load, scale, and position the enemy randomly on the screen
enemy = pygame.image.load('enemy.jpg')  
enemy = pygame.transform.scale(enemy, (50, 50))
enemy_rect = enemy.get_rect()
enemy_rect.topleft = (random.randint(0, SCREEN_WIDTH - 50), random.randint(0, SCREEN_HEIGHT - 50))
enemy_speed = [random.choice([-3, 3]), random.choice([-3, 3])]

# Initialize the score and set up the font for displaying it
score = 0
font = pygame.font.Font(None, 36)

# Main game loop
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()

    
    keys = pygame.key.get_pressed()

    # Move the character based on key presses (left, right, up, down)
    if keys[pygame.K_LEFT] and character_rect.left > 0:
        character_rect.x -= speed
    if keys[pygame.K_RIGHT] and character_rect.right < SCREEN_WIDTH:
        character_rect.x += speed
    if keys[pygame.K_UP] and character_rect.top > 0:
        character_rect.y -= speed
    if keys[pygame.K_DOWN] and character_rect.bottom < SCREEN_HEIGHT:
        character_rect.y += speed

    # Move the enemy and bounce it off the screen edges
    enemy_rect.x += enemy_speed[0]
    enemy_rect.y += enemy_speed[1]
    if enemy_rect.left <= 0 or enemy_rect.right >= SCREEN_WIDTH:
        enemy_speed[0] = -enemy_speed[0]
    if enemy_rect.top <= 0 or enemy_rect.bottom >= SCREEN_HEIGHT:
        enemy_speed[1] = -enemy_speed[1]

   # Check if the character collides with the collectible
    if character_rect.colliderect(collectible_rect):
        score += 1
        collectible_rect.topleft = (random.randint(0, SCREEN_WIDTH - 30), random.randint(0, SCREEN_HEIGHT - 30))

    # Draw the background
    screen.blit(background, (0, 0))

     # Draw the character, enemy, and collectible
    screen.blit(character, character_rect.topleft)
    screen.blit(enemy, enemy_rect.topleft)
    screen.blit(collectible, collectible_rect.topleft)

    
    score_text = font.render(f'Score: {score}', True, BLACK)
    screen.blit(score_text, (10, 10))

    # Update the display
    pygame.display.flip()

     # Limit the game to 60 frames per second
    pygame.time.Clock().tick(60)
  
        

