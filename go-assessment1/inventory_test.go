package main

import "testing"

// Test AddItem
func TestAddItem(t *testing.T) {
	inventory = []Item{}

	err := AddItem(Item{1, "Laptop", 5, 50000})

	if err != nil {
		t.Errorf("AddItem failed: %v", err)
	}

	if len(inventory) != 1 {
		t.Errorf("Expected 1 item, got %d", len(inventory))
	}
}

// Test UpdateItem
func TestUpdateItem(t *testing.T) {
	inventory = []Item{
		{1, "Laptop", 5, 50000},
	}

	err := UpdateItem(1, 10, 60000)

	if err != nil {
		t.Errorf("UpdateItem failed: %v", err)
	}

	if inventory[0].Quantity != 10 {
		t.Errorf("Expected quantity 10")
	}
}

// Test RemoveItem
func TestRemoveItem(t *testing.T) {
	inventory = []Item{
		{1, "Laptop", 5, 50000},
	}

	err := RemoveItem(1)

	if err != nil {
		t.Errorf("RemoveItem failed: %v", err)
	}

	if len(inventory) != 0 {
		t.Errorf("Expected empty inventory")
	}
}