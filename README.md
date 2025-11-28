# Algorithms2025
## CodeDoorFinder - Algorithm Explanation

## What This Does

This implements a **bidirectional exponential search** algorithm to find a hidden door along an infinite wall. You start at an unknown position and need to find the door by checking positions.

## The Strategy

The algorithm searches in an expanding pattern:

1. **Step 1 (odd):** Move **2¹ = 2** steps LEFT
2. **Step 2 (even):** Move **2² = 4** steps RIGHT  
3. **Step 3 (odd):** Move **2³ = 8** steps LEFT
4. **Step 4 (even):** Move **2⁴ = 16** steps RIGHT
5. And so on...

## Why This Works

- **Covers infinite space:** By alternating directions and doubling distance each time, you eventually reach any finite distance from your starting point
- **Exponential growth:** The doubling pattern means you don't waste too much time if the door is far away
- **Efficient:** If the door is at distance `d`, you'll find it in O(log d) steps rather than checking every position linearly

## Example Walkthrough

Starting at position 0, looking for a door at position -5:

```
Start: position 0
Step 1: Move 2 LEFT  → check -1, -2 (door not found)
Step 2: Move 4 RIGHT → check -1, 0, 1, 2 (door not found)
Step 3: Move 8 LEFT  → check 1, 0, -1, -2, -3, -4, -5 ✓ (FOUND!)
```

The algorithm guarantees you'll eventually cover every position on the infinite wall while minimizing unnecessary moves


# README: FriendsFinder

## Overview

This program finds the two closest friends (points) in a 2D coordinate system from a list of friend locations.

## Authors

Bridget Martinez and Christine Samons

## What It Does

The `nearestFriends()` method takes a list of `Point` objects (representing friend locations with x and y coordinates) and returns the two points that are closest to each other.

## Algorithm: Brute Force Approach

**Time Complexity:** O(n²)  
**Space Complexity:** O(1)

### How It Works

1. **Input validation** - Returns empty list if there are fewer than 2 friends
2. **Compare all pairs** - Uses nested loops to check every possible pair of friends
3. **Calculate distances** - For each pair, calculates the squared distance (avoids expensive square root until necessary)
4. **Track minimum** - Keeps track of the closest pair found so far
5. **Return result** - Returns a list containing the two closest points

### Distance Optimization

Instead of calculating `√(dx² + dy²)` for every comparison, the code compares squared distances (`dx² + dy²`). This avoids the expensive square root operation during comparisons and only calculates it once for the final minimum distance.

## Usage

```java
List<Point> friends = new ArrayList<>();
friends.add(new Point(0, 0));
friends.add(new Point(3, 4));
friends.add(new Point(1, 1));

List<Point> closest = FriendsFinder.nearestFriends(friends);
// Returns: [Point(0,0), Point(1,1)] - distance = √2
```

## Performance Characteristics

- **Small datasets (< 1,000 points):** Fast and efficient
- **Medium datasets (1,000-10,000 points):** Acceptable performance
- **Large datasets (> 100,000 points):** Will be slow due to O(n²) complexity

**Note:** For very large datasets, a divide-and-conquer O(n log n) algorithm would be more efficient.

# README: MySorter

## Overview

This program implements the Merge Sort algorithm to sort an array of integers in ascending order.

## Authors

Bridget Martinez and Christine Samons

## What It Does

The `sort()` method takes an integer array and sorts it in place using the classic divide-and-conquer Merge Sort algorithm.

## Algorithm: Merge Sort

**Time Complexity:** O(n log n) - guaranteed for all cases (best, average, worst)  
**Space Complexity:** O(n) - requires temporary arrays for merging  
**Stability:** Stable sort (maintains relative order of equal elements)

### How It Works

The algorithm uses three main steps:

#### 1. **Divide (MergeSort method)**
- If array has more than 1 element, split it into two halves at the midpoint
- Create two temporary arrays `b` (left half) and `c` (right half)
- Copy elements from original array into these halves

#### 2. **Conquer (Recursive calls)**
- Recursively sort the left half (`b`)
- Recursively sort the right half (`c`)
- Base case: Arrays of size 1 are already sorted

#### 3. **Combine (Merge method)**
- Merge the two sorted halves back into the original array
- Compare elements from both halves, always taking the smaller one
- Copy any remaining elements from either half

### Example Walkthrough

```
Original: [38, 27, 43, 3, 9, 82, 10]

Divide:
[38, 27, 43, 3] | [9, 82, 10]
[38, 27] [43, 3] | [9, 82] [10]
[38] [27] [43] [3] | [9] [82] [10]

Merge:
[27, 38] [3, 43] | [9, 82] [10]
[3, 27, 38, 43] | [9, 10, 82]

Final: [3, 9, 10, 27, 38, 43, 82]
```

## Usage

```java
int[] numbers = {5, 2, 8, 1, 9};
MySorter.sort(numbers);
// numbers is now: [1, 2, 5, 8, 9]
```

## Performance Characteristics

- **Consistent performance:** Always O(n log n), regardless of input
- **Good for large datasets:** Much better than O(n²) algorithms like bubble sort
- **Memory overhead:** Requires additional space for temporary arrays
- **Stable:** Preserves the order of equal elements

## Key Implementation Details

- Uses `System.arraycopy()` for efficient array copying
- Sorts in-place (modifies the original array)
- Handles arrays of any size, including edge cases (empty or single-element arrays)
