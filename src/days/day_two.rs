use std::str;

pub fn solve() {
    println!("\n---===* DAY 02 *===---");

    let ids = include_str!("../../data/02.txt").trim_end();

    let sum_part_one = part_one(ids.split(","));
    let sum_part_two = part_two(ids.split(","));

    println!("» Part 1\nThe sum is: {sum_part_one}");
    println!("» Part 2\nThe sum is: {sum_part_two}");
}

fn part_one(ids: str::Split<'_, &str>) -> usize {
    let mut sum = 0;

    for range in ids {
        let mut start: usize = 0;
        let mut end: usize = 0;

        match range.split_once("-") {
            Some((a, b)) => {
                start = a.parse().expect("Error: Start of range is not a number!");
                end = b.parse().expect("Error: End of range is not a number!");
            }
            None => eprintln!("Error: Invalid range!"),
        }

        for i in start..=end {
            let num_str = i.to_string();
            let length = num_str.len();

            if length % 2 != 0 {
                continue;
            }

            let half_length = length / 2;

            // Not used, but cool fact:
            // 123123 = 123 * 1001
            // Length of 123123: 6 => 6 / 2 = 3
            // 1001 = 10^3 + 1

            let first_half_num_str: String = num_str.chars().take(half_length).collect();
            let second_half_num_str: String = num_str.chars().skip(half_length).collect();

            if first_half_num_str == second_half_num_str {
                sum += i;
            }
        }
    }

    sum
}

fn part_two(ids: str::Split<'_, &str>) -> usize {
    let mut sum = 0;

    for range in ids {
        let mut start: usize = 0;
        let mut end: usize = 0;

        match range.split_once("-") {
            Some((a, b)) => {
                start = a.parse().expect("Error: Start of range is not a number!");
                end = b.parse().expect("Error: End of range is not a number!");
            }
            None => eprintln!("Error: Invalid range!"),
        }

        for i in start..=end {
            let num_str = i.to_string();
            let length = num_str.len();
            let mut divide_by = length;

            // brute force, because I don't care >:D
            'outer: loop {
                if divide_by < 2 {
                    break;
                }

                let length_divided = length / divide_by;
                let mut latest_part = String::new();

                for i in (0..length).step_by(length_divided) {
                    let part: String = num_str.chars().skip(i).take(length_divided).collect();

                    if latest_part.is_empty() {
                        latest_part = part;
                        continue;
                    }

                    if latest_part != part {
                        divide_by -= 1;
                        continue 'outer;
                    }
                }

                sum += i;
                break;
            }
        }
    }

    sum
}
