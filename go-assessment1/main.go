package main

import (
	"errors"
	"fmt"
)
type Item struct {
	ID       int
	Name     string
	Quantity int
	Price    float64
}
var inventory []Item

// Add
func AddItem(item Item) error {
	if item.ID <= 0 || item.Name == "" || item.Quantity < 0 || item.Price < 0 {
		return errors.New("invalid item details")
	}

	for _, v := range inventory {
		if v.ID == item.ID {
			return errors.New("item already exists")
		}
	}

	inventory = append(inventory, item)
	return nil
}
// Update
func UpdateItem(id int, quantity int, price float64) error {
	for i := range inventory {
		if inventory[i].ID == id {
			if quantity < 0 || price < 0 {
				return errors.New("invalid quantity or price")
			}

			inventory[i].Quantity = quantity
			inventory[i].Price = price
			return nil
		}
	}

	return errors.New("item not found")
}

// Remove
func RemoveItem(id int) error {
	for i, item := range inventory {
		if item.ID == id {
			inventory = append(inventory[:i], inventory[i+1:]...)
			return nil
		}
	}

	return errors.New("item not found")
}

// Display
func DisplayItems() {
	fmt.Println("\nInventory Items:")

	for _, item := range inventory {
		fmt.Printf("ID: %d, Name: %s, Quantity: %d, Price: %.2f\n",
			item.ID, item.Name, item.Quantity, item.Price)
	}
}

// Summary
type Summary struct {
	TotalValue float64
	ItemCount  int
}

// Goroutine to calculate summary
func CalculateSummary(items []Item, ch chan Summary) {
	var total float64

	for _, item := range items {
		total += float64(item.Quantity) * item.Price
	}

	ch <- Summary{
		TotalValue: total,
		ItemCount:  len(items),
	}
}

// Main function
func main() {

	// Adding items
	err := AddItem(Item{1, "Laptop", 5, 50000})
	if err != nil {
		fmt.Println(err)
	}

	err = AddItem(Item{2, "Mouse", 10, 500})
	if err != nil {
		fmt.Println(err)
	}

	err = AddItem(Item{3, "Keyboard", 7, 1000})
	if err != nil {
		fmt.Println(err)
	}

	// Display inventory
	DisplayItems()

	// Update item
	err = UpdateItem(1, 8, 55000)
	if err != nil {
		fmt.Println(err)
	}

	// Remove item
	err = RemoveItem(2)
	if err != nil {
		fmt.Println(err)
	}

	fmt.Println("\nAfter Update and Remove:")
	DisplayItems()

	// Concurrent summary using goroutine and channel
	ch := make(chan Summary)

	go CalculateSummary(inventory, ch)

	result := <-ch

	fmt.Println("\nInventory Summary:")
	fmt.Println("Total Inventory Value:", result.TotalValue)
	fmt.Println("Total Item Count:", result.ItemCount)

	close(ch)
}