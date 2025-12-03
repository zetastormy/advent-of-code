use std::str;

pub fn solve() {
    println!("\n---===* DAY 01 *===---");

    let rotations = include_str!("../../data/01.txt").trim_end();

    let password_part_one = part_one(rotations.split("\n"));
    let password_part_two = part_two(rotations.split("\n"));

    println!("» Part 1\nThe password is: {password_part_one}");
    println!("» Part 2\nThe new password is: {password_part_two}");
}

fn part_one(rotations: str::Split<'_, &str>) -> i32 {
    let mut dial = 50;
    let mut password = 0;

    for rotation in rotations {
        let direction = rotation.chars().next().unwrap();
        let amount: String = rotation.chars().skip(1).collect();
        let amount: i32 = amount
            .parse()
            .expect("The rotation amount must be a number!");

        match direction {
            'L' => dial = ((dial - amount) % 100 + 100) % 100,
            'R' => dial = ((dial + amount) % 100 + 100) % 100,
            _ => eprintln!("Error: Direction is not L nor R!"),
        }

        if dial == 0 {
            password += 1;
        }
    }

    password
}

fn part_two(rotations: str::Split<'_, &str>) -> i32 {
    let mut dial_part_two = 50;
    let mut password_part_two = 0;

    for rotation in rotations {
        let direction = rotation.chars().next().unwrap();
        let amount: String = rotation.chars().skip(1).collect();
        let amount: i32 = amount
            .parse()
            .expect("The rotation amount must be a number!");

        match direction {
            'L' => {
                dial_part_two -= amount;

                while dial_part_two < 0 {
                    dial_part_two += 100;
                    password_part_two += 1
                }
            }
            'R' => {
                dial_part_two += amount;

                while dial_part_two > 99 {
                    dial_part_two -= 100;
                    password_part_two += 1
                }
            }
            _ => eprintln!("Error: Direction is not L nor R!"),
        }
    }

    password_part_two
}
