const { PrismaClient } = require('@prisma/client');
const bcrypt = require('bcrypt');
const prisma = new PrismaClient();
const saltRounds = 10;

async function main() {
    // use this to erase the data first and populate again
    await prisma.user.deleteMany();
    console.log('Deleted all existing users.');

    const users = [
        { 
            username: "alice", 
            email: 'alice@example.com', 
            password: 'password',
            profile: {
                fullName: "Alice Smith",
                gender: "FEMALE",
                employmentType: "PERMANENT",
                designation: "Team Lead",
                dateOfBirth: new Date("1985-05-10")
            }
        },
        { 
            username: "bob", 
            email: 'bob@example.com', 
            password: 'password',
            profile: {
                fullName: "Bob Johnson",
                gender: "MALE",
                employmentType: "CONTRACTUAL",
                designation: "Software Engineer",
                dateOfBirth: new Date("1990-08-15")
            }
        },
        { 
            username: "sam", 
            email: 'charlie@example.com', 
            password: 'password',
            profile: {
                fullName: "Sam Wilson",
                gender: "OTHER",
                employmentType: "INTERNSHIP",
                designation: "Intern",
                dateOfBirth: new Date("2000-01-20")
            }
        },
    ];

    // Insert users into the database
    for (const user of users) {
        await prisma.user.create({
            data: {
                username: user.username,
                email: user.email,
                password: await bcrypt.hash(user.password, saltRounds),
                profile: {
                    create: user.profile
                }
            },
        });
        console.log(`Created user: ${user.email}`);
    }
}

main()
    .catch((e) => {
        console.error(e);
        process.exit(1);
    })
    .finally(async () => {
        await prisma.$disconnect();
    });