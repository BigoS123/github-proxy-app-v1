# GitHub Proxy application
___

## Description

Application that serves as a proxy for a GitHub API

## Requirements
For building and running the application you'll need:
- JDK 18
- Maven 3

# Dependencies

Browse the Maven pom.xml file for details of libraries and versions used.

## Features
List all user's repositories wich are not fork with following data:
- Repository name
- Owner login
- List of branches with last commit SHA

## How to use
Core function:
- Url: http://localhost:8080/api/v1/userrepo
- Method: GET
- Params: "username" - String
- Headers: "Accept: application/json"

Expected response:

```json
[
  {
    "repositoryName": "dummyName1",
    "repositoryOwner": "dummyOwner2",
    "repositoryBranchData": [
      {
        "lastCommitSHA": "c640b0aa0c8b8df1ac111325280c4e418e9a82a2",
        "name": "dummyBranch"
      },
      {
        "lastCommitSHA": "017e403e3e8db3287cdb5c7269812dbebe003111",
        "name": "dummyBranch"
      }
    ]
  },
  {
    "repositoryName": "dummyName2",
    "repositoryOwner": "dummyOwner1",
    "repositoryBranchData": [
      {
        "lastCommitSHA": "66c9584b8e390b347efbbbbfcda888e93366c59d",
        "name": "dummyBranch"
      },
      {
        "lastCommitSHA": "ccc1c4d1f09c34d28f5469b8e0595fb6fa658af4",
        "name": "dummyBranch"
      }
    ]
  }
]
```

Swagger docs:
- Url: http://localhost:8080/v2/api-docs
- Method: GET


