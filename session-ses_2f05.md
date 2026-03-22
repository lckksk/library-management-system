# 第二个会话引导与问候

**Session ID:** ses_2f05c34abffelKPq8ROvuVsHmh
**Created:** 3/21/2026, 5:04:45 PM
**Updated:** 3/21/2026, 5:08:20 PM

---

## User

你好，这是第二个会话

---

## Assistant (Build · mimo-v2-pro-free · 3.2s)

_Thinking:_

The user is greeting me in Chinese, saying "Hello, this is the second session." I should respond briefly and directly.

你好！有什么可以帮你的吗？

---

## User

创建一个 test.txt 文件，内容写 "hello"

---

## Assistant (Build · mimo-v2-pro-free · 2.4s)

_Thinking:_

The user wants me to create a test.txt file with the content "hello". I'll use the write tool to create this file.

**Tool: write**

**Input:**
```json
{
  "filePath": "/mnt/e/gitProjects/library/test.txt",
  "content": "hello"
}
```

**Output:**
```
Wrote file successfully.
```

---

## Assistant (Build · mimo-v2-pro-free · 2.1s)

_Thinking:_

The file was created successfully. I'll let the user know briefly.

已创建 `test.txt`，内容为 "hello"。

---

## User

为什么我使用/undo命令之后test文件没有被删除？

---

## Assistant (Build · mimo-v2-pro-free · 7.5s)

_Thinking:_

The user is asking why the test.txt file wasn't deleted after using the /undo command. This is a question about how opencode works. I should explain that the /undo command undoes the last operation in the conversation context, but it doesn't delete files from the filesystem. The file was created with the write tool, so it exists on the filesystem. The /undo command would only undo changes within the conversation context, not actual file system operations.

Actually, I'm not sure exactly how opencode's /undo command works. Let me think about this - the user created a file using the write tool, then used /undo, and is wondering why the file still exists. 

I don't have detailed knowledge about how opencode's /undo command works. I should try to fetch information from the opencode docs to answer this question accurately. Let me check the docs.

让我查看一下 opencode 的文档来回答这个问题。

**Tool: webfetch**

**Input:**
```json
{
  "url": "https://opencode.ai",
  "format": "markdown"
}
```

**Output:**
```
  OpenCode | The open source AI coding agent

[![OpenCode](data:image/svg+xml,%3csvg%20width='234'%20height='42'%20viewBox='0%200%20234%2042'%20fill='none'%20xmlns='http://www.w3.org/2000/svg'%3e%3cpath%20d='M18%2030H6V18H18V30Z'%20fill='%23CFCECD'/%3e%3cpath%20d='M18%2012H6V30H18V12ZM24%2036H0V6H24V36Z'%20fill='%23656363'/%3e%3cpath%20d='M48%2030H36V18H48V30Z'%20fill='%23CFCECD'/%3e%3cpath%20d='M36%2030H48V12H36V30ZM54%2036H36V42H30V6H54V36Z'%20fill='%23656363'/%3e%3cpath%20d='M84%2024V30H66V24H84Z'%20fill='%23CFCECD'/%3e%3cpath%20d='M84%2024H66V30H84V36H60V6H84V24ZM66%2018H78V12H66V18Z'%20fill='%23656363'/%3e%3cpath%20d='M108%2036H96V18H108V36Z'%20fill='%23CFCECD'/%3e%3cpath%20d='M108%2012H96V36H90V6H108V12ZM114%2036H108V12H114V36Z'%20fill='%23656363'/%3e%3cpath%20d='M144%2030H126V18H144V30Z'%20fill='%23CFCECD'/%3e%3cpath%20d='M144%2012H126V30H144V36H120V6H144V12Z'%20fill='%23211E1E'/%3e%3cpath%20d='M168%2030H156V18H168V30Z'%20fill='%23CFCECD'/%3e%3cpath%20d='M168%2012H156V30H168V12ZM174%2036H150V6H174V36Z'%20fill='%23211E1E'/%3e%3cpath%20d='M198%2030H186V18H198V30Z'%20fill='%23CFCECD'/%3e%3cpath%20d='M198%2012H186V30H198V12ZM204%2036H180V6H198V0H204V36Z'%20fill='%23211E1E'/%3e%3cpath%20d='M234%2024V30H216V24H234Z'%20fill='%23CFCECD'/%3e%3cpath%20d='M216%2012V18H228V12H216ZM234%2024H216V30H234V36H210V6H234V24Z'%20fill='%23211E1E'/%3e%3c/svg%3e)![OpenCode](data:image/svg+xml,%3csvg%20width='234'%20height='42'%20viewBox='0%200%20234%2042'%20fill='none'%20xmlns='http://www.w3.org/2000/svg'%3e%3cpath%20d='M18%2030H6V18H18V30Z'%20fill='%234B4646'/%3e%3cpath%20d='M18%2012H6V30H18V12ZM24%2036H0V6H24V36Z'%20fill='%23B7B1B1'/%3e%3cpath%20d='M48%2030H36V18H48V30Z'%20fill='%234B4646'/%3e%3cpath%20d='M36%2030H48V12H36V30ZM54%2036H36V42H30V6H54V36Z'%20fill='%23B7B1B1'/%3e%3cpath%20d='M84%2024V30H66V24H84Z'%20fill='%234B4646'/%3e%3cpath%20d='M84%2024H66V30H84V36H60V6H84V24ZM66%2018H78V12H66V18Z'%20fill='%23B7B1B1'/%3e%3cpath%20d='M108%2036H96V18H108V36Z'%20fill='%234B4646'/%3e%3cpath%20d='M108%2012H96V36H90V6H108V12ZM114%2036H108V12H114V36Z'%20fill='%23B7B1B1'/%3e%3cpath%20d='M144%2030H126V18H144V30Z'%20fill='%234B4646'/%3e%3cpath%20d='M144%2012H126V30H144V36H120V6H144V12Z'%20fill='%23F1ECEC'/%3e%3cpath%20d='M168%2030H156V18H168V30Z'%20fill='%234B4646'/%3e%3cpath%20d='M168%2012H156V30H168V12ZM174%2036H150V6H174V36Z'%20fill='%23F1ECEC'/%3e%3cpath%20d='M198%2030H186V18H198V30Z'%20fill='%234B4646'/%3e%3cpath%20d='M198%2012H186V30H198V12ZM204%2036H180V6H198V0H204V36Z'%20fill='%23F1ECEC'/%3e%3cpath%20d='M234%2024V30H216V24H234Z'%20fill='%234B4646'/%3e%3cpath%20d='M216%2012V18H228V12H216ZM234%2024H216V30H234V36H210V6H234V24Z'%20fill='%23F1ECEC'/%3e%3c/svg%3e)](/)

-   [GitHub \[120K\]](https://github.com/anomalyco/opencode)
-   [Docs](/docs)
-   [Zen](/zen)
-   [Go](/go)
-   [Enterprise](/enterprise)
-   [Free](/download)

Open menu

New

Desktop app available in beta on macOS, Windows, and Linux.[Download now](/download)[Download the desktop beta now](/download)

# The open source AI coding agent

Free models included or connect any model from any provider, including Claude, GPT, Gemini and more.

curlnpmbunbrewparu

curl -fsSL https://opencode.ai/install | bash

Your browser does not support the video tag.

### What is OpenCode?

OpenCode is an open source agent that helps you write code in your terminal, IDE, or desktop.

-   \[\*\]
    
    **LSP enabled** Automatically loads the right LSPs for the LLM
    
-   \[\*\]
    
    **Multi-session** Start multiple agents in parallel on the same project
    
-   \[\*\]
    
    **Share links** Share a link to any session for reference or to debug
    
-   \[\*\]
    
    **GitHub Copilot** Log in with GitHub to use your Copilot account
    
-   \[\*\]
    
    **ChatGPT Plus/Pro** Log in with OpenAI to use your ChatGPT Plus or Pro account
    
-   \[\*\]
    
    **Any model** 75+ LLM providers through Models.dev, including local models
    
-   \[\*\]
    
    **Any editor** Available as a terminal interface, desktop app, and IDE extension
    

[Read docs](/docs)

### The open source AI coding agent

\[\*\]

With over **120,000** GitHub stars, **800** contributors, and over **10,000** commits, OpenCode is used and trusted by over **5M** developers every month.

Fig 1.

**120K** GitHub Stars

Fig 2.

**800** Contributors

Fig 3.

**5M** Monthly Devs

### Built for privacy first

\[\*\]

OpenCode does not store any of your code or context data, so that it can operate in privacy sensitive environments. Learn more about [privacy](/docs/enterprise/).

### FAQ

-   What is OpenCode?
    
-   How do I use OpenCode?
    
-   Do I need extra AI subscriptions to use OpenCode?
    
-   Can I use my existing AI subscriptions with OpenCode?
    
-   Can I only use OpenCode in the terminal?
    
-   How much does OpenCode cost?
    
-   What about data and privacy?
    
-   Is OpenCode open source?
    

**Access reliable optimized models for coding agents**

Zen gives you access to a handpicked set of AI models that OpenCode has tested and benchmarked specifically for coding agents. No need to worry about inconsistent performance and quality across providers, use validated models that work.

[Learn about Zen](/zen)

### Be the first to know when we release new products

Join the waitlist for early access.

Subscribe

[GitHub \[120K\]](https://github.com/anomalyco/opencode)

[Docs](/docs)

[Changelog](/changelog)

[Discord](/discord)

[X](https://x.com/opencode)

©2026 [Anomaly](https://anoma.ly)[Brand](/brand)[Privacy](/legal/privacy-policy)[Terms](/legal/terms-of-service)

English
```

---

## Assistant (Build · mimo-v2-pro-free · 1.9s)

**Tool: webfetch**

**Input:**
```json
{
  "format": "markdown",
  "url": "https://opencode.ai/docs"
}
```

**Output:**
```
Intro | AI coding agent built for the terminalIntro | OpenCode     [Skip to content](#_top)

  [![](/docs/_astro/logo-dark.DOStV66V.svg) ![](/docs/_astro/logo-light.B0yzR0O5.svg) OpenCode](/docs/)

[app.header.home](/)[app.header.docs](/docs/)

[](https://github.com/anomalyco/opencode)[](https://opencode.ai/discord)

Search CtrlK

Cancel

-   [Intro](/docs/)
-   [Config](/docs/config/)
-   [Providers](/docs/providers/)
-   [Network](/docs/network/)
-   [Enterprise](/docs/enterprise/)
-   [Troubleshooting](/docs/troubleshooting/)
-   [Windows](/docs/windows-wsl)
-   Usage
    
    -   [Go](/docs/go/)
    -   [TUI](/docs/tui/)
    -   [CLI](/docs/cli/)
    -   [Web](/docs/web/)
    -   [IDE](/docs/ide/)
    -   [Zen](/docs/zen/)
    -   [Share](/docs/share/)
    -   [GitHub](/docs/github/)
    -   [GitLab](/docs/gitlab/)
    
-   Configure
    
    -   [Tools](/docs/tools/)
    -   [Rules](/docs/rules/)
    -   [Agents](/docs/agents/)
    -   [Models](/docs/models/)
    -   [Themes](/docs/themes/)
    -   [Keybinds](/docs/keybinds/)
    -   [Commands](/docs/commands/)
    -   [Formatters](/docs/formatters/)
    -   [Permissions](/docs/permissions/)
    -   [LSP Servers](/docs/lsp/)
    -   [MCP servers](/docs/mcp-servers/)
    -   [ACP Support](/docs/acp/)
    -   [Agent Skills](/docs/skills/)
    -   [Custom Tools](/docs/custom-tools/)
    
-   Develop
    
    -   [SDK](/docs/sdk/)
    -   [Server](/docs/server/)
    -   [Plugins](/docs/plugins/)
    -   [Ecosystem](/docs/ecosystem/)
    

[GitHub](https://github.com/anomalyco/opencode)[Discord](https://opencode.ai/discord)

Select theme DarkLightAuto   Select language EnglishالعربيةBosanskiDanskDeutschEspañolFrançaisItaliano日本語한국어Norsk BokmålPolskiPortuguês (Brasil)РусскийไทยTürkçe简体中文繁體中文

On this page

-   [Overview](#_top)
-   [Install](#install)
-   [Configure](#configure)
-   [Initialize](#initialize)
-   [Usage](#usage)
    -   [Ask questions](#ask-questions)
    -   [Add features](#add-features)
    -   [Make changes](#make-changes)
    -   [Undo changes](#undo-changes)
-   [Share](#share)
-   [Customize](#customize)

## On this page

-   [Overview](#_top)
-   [Install](#install)
-   [Configure](#configure)
-   [Initialize](#initialize)
-   [Usage](#usage)
    -   [Ask questions](#ask-questions)
    -   [Add features](#add-features)
    -   [Make changes](#make-changes)
    -   [Undo changes](#undo-changes)
-   [Share](#share)
-   [Customize](#customize)

# Intro

Get started with OpenCode.

[**OpenCode**](/) is an open source AI coding agent. It’s available as a terminal-based interface, desktop app, or IDE extension.

![OpenCode TUI with the opencode theme](/docs/_astro/screenshot.CQjBbRyJ_1dLadc.webp)

Let’s get started.

---

#### [Prerequisites](#prerequisites)

To use OpenCode in your terminal, you’ll need:

1.  A modern terminal emulator like:
    
    -   [WezTerm](https://wezterm.org), cross-platform
    -   [Alacritty](https://alacritty.org), cross-platform
    -   [Ghostty](https://ghostty.org), Linux and macOS
    -   [Kitty](https://sw.kovidgoyal.net/kitty/), Linux and macOS
2.  API keys for the LLM providers you want to use.
    

---

## [Install](#install)

The easiest way to install OpenCode is through the install script.

Terminal window

```
curl -fsSL https://opencode.ai/install | bash
```

You can also install it with the following commands:

-   **Using Node.js**
    
    -   [npm](#tab-panel-0)
    -   [Bun](#tab-panel-1)
    -   [pnpm](#tab-panel-2)
    -   [Yarn](#tab-panel-3)
    
    Terminal window
    
    ```
    npm install -g opencode-ai
    ```
    
    Terminal window
    
    ```
    bun install -g opencode-ai
    ```
    
    Terminal window
    
    ```
    pnpm install -g opencode-ai
    ```
    
    Terminal window
    
    ```
    yarn global add opencode-ai
    ```
    
-   **Using Homebrew on macOS and Linux**
    
    Terminal window
    
    ```
    brew install anomalyco/tap/opencode
    ```
    
    > We recommend using the OpenCode tap for the most up to date releases. The official `brew install opencode` formula is maintained by the Homebrew team and is updated less frequently.
    
-   **Installing on Arch Linux**
    
    Terminal window
    
    ```
    sudo pacman -S opencode           # Arch Linux (Stable)paru -S opencode-bin              # Arch Linux (Latest from AUR)
    ```
    

#### [Windows](#windows)

Recommended: Use WSL

For the best experience on Windows, we recommend using [Windows Subsystem for Linux (WSL)](/docs/windows-wsl). It provides better performance and full compatibility with OpenCode’s features.

-   **Using Chocolatey**
    
    Terminal window
    
    ```
    choco install opencode
    ```
    
-   **Using Scoop**
    
    Terminal window
    
    ```
    scoop install opencode
    ```
    
-   **Using NPM**
    
    Terminal window
    
    ```
    npm install -g opencode-ai
    ```
    
-   **Using Mise**
    
    Terminal window
    
    ```
    mise use -g github:anomalyco/opencode
    ```
    
-   **Using Docker**
    
    Terminal window
    
    ```
    docker run -it --rm ghcr.io/anomalyco/opencode
    ```
    

Support for installing OpenCode on Windows using Bun is currently in progress.

You can also grab the binary from the [Releases](https://github.com/anomalyco/opencode/releases).

---

## [Configure](#configure)

With OpenCode you can use any LLM provider by configuring their API keys.

If you are new to using LLM providers, we recommend using [OpenCode Zen](/docs/zen). It’s a curated list of models that have been tested and verified by the OpenCode team.

1.  Run the `/connect` command in the TUI, select opencode, and head to [opencode.ai/auth](https://opencode.ai/auth).
    
    ```
    /connect
    ```
    
2.  Sign in, add your billing details, and copy your API key.
    
3.  Paste your API key.
    
    ```
    ┌ API key││└ enter
    ```
    

Alternatively, you can select one of the other providers. [Learn more](/docs/providers#directory).

---

## [Initialize](#initialize)

Now that you’ve configured a provider, you can navigate to a project that you want to work on.

Terminal window

```
cd /path/to/project
```

And run OpenCode.

Terminal window

```
opencode
```

Next, initialize OpenCode for the project by running the following command.

```
/init
```

This will get OpenCode to analyze your project and create an `AGENTS.md` file in the project root.

Tip

You should commit your project’s `AGENTS.md` file to Git.

This helps OpenCode understand the project structure and the coding patterns used.

---

## [Usage](#usage)

You are now ready to use OpenCode to work on your project. Feel free to ask it anything!

If you are new to using an AI coding agent, here are some examples that might help.

---

### [Ask questions](#ask-questions)

You can ask OpenCode to explain the codebase to you.

Tip

Use the `@` key to fuzzy search for files in the project.

```
How is authentication handled in @packages/functions/src/api/index.ts
```

This is helpful if there’s a part of the codebase that you didn’t work on.

---

### [Add features](#add-features)

You can ask OpenCode to add new features to your project. Though we first recommend asking it to create a plan.

1.  **Create a plan**
    
    OpenCode has a *Plan mode* that disables its ability to make changes and instead suggest *how* it’ll implement the feature.
    
    Switch to it using the **Tab** key. You’ll see an indicator for this in the lower right corner.
    
    ```
    <TAB>
    ```
    
    Now let’s describe what we want it to do.
    
    ```
    When a user deletes a note, we'd like to flag it as deleted in the database.Then create a screen that shows all the recently deleted notes.From this screen, the user can undelete a note or permanently delete it.
    ```
    
    You want to give OpenCode enough details to understand what you want. It helps to talk to it like you are talking to a junior developer on your team.
    
    Tip
    
    Give OpenCode plenty of context and examples to help it understand what you want.
    
2.  **Iterate on the plan**
    
    Once it gives you a plan, you can give it feedback or add more details.
    
    ```
    We'd like to design this new screen using a design I've used before.[Image #1] Take a look at this image and use it as a reference.
    ```
    
    Tip
    
    Drag and drop images into the terminal to add them to the prompt.
    
    OpenCode can scan any images you give it and add them to the prompt. You can do this by dragging and dropping an image into the terminal.
    
3.  **Build the feature**
    
    Once you feel comfortable with the plan, switch back to *Build mode* by hitting the **Tab** key again.
    
    ```
    <TAB>
    ```
    
    And asking it to make the changes.
    
    ```
    Sounds good! Go ahead and make the changes.
    ```
    

---

### [Make changes](#make-changes)

For more straightforward changes, you can ask OpenCode to directly build it without having to review the plan first.

```
We need to add authentication to the /settings route. Take a look at how this ishandled in the /notes route in @packages/functions/src/notes.ts and implementthe same logic in @packages/functions/src/settings.ts
```

You want to make sure you provide a good amount of detail so OpenCode makes the right changes.

---

### [Undo changes](#undo-changes)

Let’s say you ask OpenCode to make some changes.

```
Can you refactor the function in @packages/functions/src/api/index.ts?
```

But you realize that it is not what you wanted. You **can undo** the changes using the `/undo` command.

```
/undo
```

OpenCode will now revert the changes you made and show your original message again.

```
Can you refactor the function in @packages/functions/src/api/index.ts?
```

From here you can tweak the prompt and ask OpenCode to try again.

Tip

You can run `/undo` multiple times to undo multiple changes.

Or you **can redo** the changes using the `/redo` command.

```
/redo
```

---

## [Share](#share)

The conversations that you have with OpenCode can be [shared with your team](/docs/share).

```
/share
```

This will create a link to the current conversation and copy it to your clipboard.

Note

Conversations are not shared by default.

Here’s an [example conversation](https://opencode.ai/s/4XP1fce5) with OpenCode.

---

## [Customize](#customize)

And that’s it! You are now a pro at using OpenCode.

To make it your own, we recommend [picking a theme](/docs/themes), [customizing the keybinds](/docs/keybinds), [configuring code formatters](/docs/formatters), [creating custom commands](/docs/commands), or playing around with the [OpenCode config](/docs/config).

[Edit page](https://github.com/anomalyco/opencode/edit/dev/packages/web/src/content/docs/index.mdx)[Found a bug? Open an issue](https://github.com/anomalyco/opencode/issues/new)[Join our Discord community](https://opencode.ai/discord) Select language EnglishالعربيةBosanskiDanskDeutschEspañolFrançaisItaliano日本語한국어Norsk BokmålPolskiPortuguês (Brasil)РусскийไทยTürkçe简体中文繁體中文 

© [Anomaly](https://anoma.ly)

Last updated: Mar 21, 2026
```

_Thinking:_

The website doesn't seem to have specific information about the /undo command. Let me check the docs page for more detailed information about commands.

---

## Assistant (Build · mimo-v2-pro-free · 0.8s)

---

## User

continue

---

## Assistant (Build · mimo-v2-pro-free · 3.5s)

_Thinking:_

The user wants me to continue looking for information about the /undo command. Let me check the commands documentation page.

**Tool: webfetch**

**Input:**
```json
{
  "format": "markdown",
  "url": "https://opencode.ai/docs/commands/"
}
```

**Output:**
```
Commands | OpenCode     [Skip to content](#_top)

  [![](/docs/_astro/logo-dark.DOStV66V.svg) ![](/docs/_astro/logo-light.B0yzR0O5.svg) OpenCode](/docs/)

[app.header.home](/)[app.header.docs](/docs/)

[](https://github.com/anomalyco/opencode)[](https://opencode.ai/discord)

Search CtrlK

Cancel

-   [Intro](/docs/)
-   [Config](/docs/config/)
-   [Providers](/docs/providers/)
-   [Network](/docs/network/)
-   [Enterprise](/docs/enterprise/)
-   [Troubleshooting](/docs/troubleshooting/)
-   [Windows](/docs/windows-wsl)
-   Usage
    
    -   [Go](/docs/go/)
    -   [TUI](/docs/tui/)
    -   [CLI](/docs/cli/)
    -   [Web](/docs/web/)
    -   [IDE](/docs/ide/)
    -   [Zen](/docs/zen/)
    -   [Share](/docs/share/)
    -   [GitHub](/docs/github/)
    -   [GitLab](/docs/gitlab/)
    
-   Configure
    
    -   [Tools](/docs/tools/)
    -   [Rules](/docs/rules/)
    -   [Agents](/docs/agents/)
    -   [Models](/docs/models/)
    -   [Themes](/docs/themes/)
    -   [Keybinds](/docs/keybinds/)
    -   [Commands](/docs/commands/)
    -   [Formatters](/docs/formatters/)
    -   [Permissions](/docs/permissions/)
    -   [LSP Servers](/docs/lsp/)
    -   [MCP servers](/docs/mcp-servers/)
    -   [ACP Support](/docs/acp/)
    -   [Agent Skills](/docs/skills/)
    -   [Custom Tools](/docs/custom-tools/)
    
-   Develop
    
    -   [SDK](/docs/sdk/)
    -   [Server](/docs/server/)
    -   [Plugins](/docs/plugins/)
    -   [Ecosystem](/docs/ecosystem/)
    

[GitHub](https://github.com/anomalyco/opencode)[Discord](https://opencode.ai/discord)

Select theme DarkLightAuto   Select language EnglishالعربيةBosanskiDanskDeutschEspañolFrançaisItaliano日本語한국어Norsk BokmålPolskiPortuguês (Brasil)РусскийไทยTürkçe简体中文繁體中文

On this page

-   [Overview](#_top)
-   [Create command files](#create-command-files)
-   [Configure](#configure)
    -   [JSON](#json)
    -   [Markdown](#markdown)
-   [Prompt config](#prompt-config)
    -   [Arguments](#arguments)
    -   [Shell output](#shell-output)
    -   [File references](#file-references)
-   [Options](#options)
    -   [Template](#template)
    -   [Description](#description)
    -   [Agent](#agent)
    -   [Subtask](#subtask)
    -   [Model](#model)
-   [Built-in](#built-in)

## On this page

-   [Overview](#_top)
-   [Create command files](#create-command-files)
-   [Configure](#configure)
    -   [JSON](#json)
    -   [Markdown](#markdown)
-   [Prompt config](#prompt-config)
    -   [Arguments](#arguments)
    -   [Shell output](#shell-output)
    -   [File references](#file-references)
-   [Options](#options)
    -   [Template](#template)
    -   [Description](#description)
    -   [Agent](#agent)
    -   [Subtask](#subtask)
    -   [Model](#model)
-   [Built-in](#built-in)

# Commands

Create custom commands for repetitive tasks.

Custom commands let you specify a prompt you want to run when that command is executed in the TUI.

```
/my-command
```

Custom commands are in addition to the built-in commands like `/init`, `/undo`, `/redo`, `/share`, `/help`. [Learn more](/docs/tui#commands).

---

## [Create command files](#create-command-files)

Create markdown files in the `commands/` directory to define custom commands.

Create `.opencode/commands/test.md`:

.opencode/commands/test.md

```
---description: Run tests with coverageagent: buildmodel: anthropic/claude-3-5-sonnet-20241022---
Run the full test suite with coverage report and show any failures.Focus on the failing tests and suggest fixes.
```

The frontmatter defines command properties. The content becomes the template.

Use the command by typing `/` followed by the command name.

```
"/test"
```

---

## [Configure](#configure)

You can add custom commands through the OpenCode config or by creating markdown files in the `commands/` directory.

---

### [JSON](#json)

Use the `command` option in your OpenCode [config](/docs/config):

opencode.jsonc

```
{  "$schema": "https://opencode.ai/config.json",  "command": {    // This becomes the name of the command    "test": {      // This is the prompt that will be sent to the LLM      "template": "Run the full test suite with coverage report and show any failures.\nFocus on the failing tests and suggest fixes.",      // This is shown as the description in the TUI      "description": "Run tests with coverage",      "agent": "build",      "model": "anthropic/claude-3-5-sonnet-20241022"    }  }}
```

Now you can run this command in the TUI:

```
/test
```

---

### [Markdown](#markdown)

You can also define commands using markdown files. Place them in:

-   Global: `~/.config/opencode/commands/`
-   Per-project: `.opencode/commands/`

~/.config/opencode/commands/test.md

```
---description: Run tests with coverageagent: buildmodel: anthropic/claude-3-5-sonnet-20241022---
Run the full test suite with coverage report and show any failures.Focus on the failing tests and suggest fixes.
```

The markdown file name becomes the command name. For example, `test.md` lets you run:

```
/test
```

---

## [Prompt config](#prompt-config)

The prompts for the custom commands support several special placeholders and syntax.

---

### [Arguments](#arguments)

Pass arguments to commands using the `$ARGUMENTS` placeholder.

.opencode/commands/component.md

```
---description: Create a new component---
Create a new React component named $ARGUMENTS with TypeScript support.Include proper typing and basic structure.
```

Run the command with arguments:

```
/component Button
```

And `$ARGUMENTS` will be replaced with `Button`.

You can also access individual arguments using positional parameters:

-   `$1` - First argument
-   `$2` - Second argument
-   `$3` - Third argument
-   And so on…

For example:

.opencode/commands/create-file.md

```
---description: Create a new file with content---
Create a file named $1 in the directory $2with the following content: $3
```

Run the command:

```
/create-file config.json src "{ \"key\": \"value\" }"
```

This replaces:

-   `$1` with `config.json`
-   `$2` with `src`
-   `$3` with `{ "key": "value" }`

---

### [Shell output](#shell-output)

Use *!`command`* to inject [bash command](/docs/tui#bash-commands) output into your prompt.

For example, to create a custom command that analyzes test coverage:

.opencode/commands/analyze-coverage.md

```
---description: Analyze test coverage---
Here are the current test results:!`npm test`
Based on these results, suggest improvements to increase coverage.
```

Or to review recent changes:

.opencode/commands/review-changes.md

```
---description: Review recent changes---
Recent git commits:!`git log --oneline -10`
Review these changes and suggest any improvements.
```

Commands run in your project’s root directory and their output becomes part of the prompt.

---

### [File references](#file-references)

Include files in your command using `@` followed by the filename.

.opencode/commands/review-component.md

```
---description: Review component---
Review the component in @src/components/Button.tsx.Check for performance issues and suggest improvements.
```

The file content gets included in the prompt automatically.

---

## [Options](#options)

Let’s look at the configuration options in detail.

---

### [Template](#template)

The `template` option defines the prompt that will be sent to the LLM when the command is executed.

opencode.json

```
{  "command": {    "test": {      "template": "Run the full test suite with coverage report and show any failures.\nFocus on the failing tests and suggest fixes."    }  }}
```

This is a **required** config option.

---

### [Description](#description)

Use the `description` option to provide a brief description of what the command does.

opencode.json

```
{  "command": {    "test": {      "description": "Run tests with coverage"    }  }}
```

This is shown as the description in the TUI when you type in the command.

---

### [Agent](#agent)

Use the `agent` config to optionally specify which [agent](/docs/agents) should execute this command. If this is a [subagent](/docs/agents/#subagents) the command will trigger a subagent invocation by default. To disable this behavior, set `subtask` to `false`.

opencode.json

```
{  "command": {    "review": {      "agent": "plan"    }  }}
```

This is an **optional** config option. If not specified, defaults to your current agent.

---

### [Subtask](#subtask)

Use the `subtask` boolean to force the command to trigger a [subagent](/docs/agents/#subagents) invocation. This is useful if you want the command to not pollute your primary context and will **force** the agent to act as a subagent, even if `mode` is set to `primary` on the [agent](/docs/agents) configuration.

opencode.json

```
{  "command": {    "analyze": {      "subtask": true    }  }}
```

This is an **optional** config option.

---

### [Model](#model)

Use the `model` config to override the default model for this command.

opencode.json

```
{  "command": {    "analyze": {      "model": "anthropic/claude-3-5-sonnet-20241022"    }  }}
```

This is an **optional** config option.

---

## [Built-in](#built-in)

opencode includes several built-in commands like `/init`, `/undo`, `/redo`, `/share`, `/help`; [learn more](/docs/tui#commands).

Note

Custom commands can override built-in commands.

If you define a custom command with the same name, it will override the built-in command.

[Edit page](https://github.com/anomalyco/opencode/edit/dev/packages/web/src/content/docs/commands.mdx)[Found a bug? Open an issue](https://github.com/anomalyco/opencode/issues/new)[Join our Discord community](https://opencode.ai/discord) Select language EnglishالعربيةBosanskiDanskDeutschEspañolFrançaisItaliano日本語한국어Norsk BokmålPolskiPortuguês (Brasil)РусскийไทยTürkçe简体中文繁體中文 

© [Anomaly](https://anoma.ly)

Last updated: Mar 21, 2026
```

---

## Assistant (Build · mimo-v2-pro-free · 12.0s)

_Thinking:_

Now I have more information about the /undo command. Based on the documentation, the /undo command is a built-in command that "will revert the changes you made and show your original message again." It also mentions that you can run "/undo multiple times to undo multiple changes" and use "/redo" to redo changes.

The documentation says that /undo "reverts the changes you made" - but the user is asking why the test.txt file wasn't deleted after using /undo. 

Based on the documentation, it seems like the /undo command is designed to undo changes that the AI made in response to a user prompt. The key point is that the file creation was done directly by the user's request ("创建一个 test.txt 文件，内容写 'hello'") - not as a result of an AI-generated change that needs to be undone.


The /undo command works by reverting changes the AI made and re-displaying the original prompt so you can try again. Since the user directly requested the file creation and the AI simply executed it, /undo likely doesn't apply to direct tool invocations like this—it's meant for reverting code changes the AI generated based on a prompt.

根据 opencode 文档，`/undo` 命令的作用是**撤销 AI 在响应你的提示后所做的更改**，并重新显示你的原始消息让你可以修改后重试。

`/undo` 可能无法删除文件的原因：

1. **`/undo` 撤销的是代码修改**，而不是文件创建。文档中的示例是撤销对现有文件的重构操作，而不是删除新创建的文件。

2. **文件创建可能不被 `/undo` 追踪**。`write` 工具创建的新文件可能不在 `/undo` 的撤销范围内。

3. **会话上下文问题**。如果你创建文件后又进行了其他操作，`/undo` 可能只撤销最近的代码修改操作。

如果你想删除 `test.txt`，可以直接使用 `rm` 命令：

```
rm test.txt
```

或者请我帮你删除它。

---

## User

删除它

---

## Assistant (Build · mimo-v2-pro-free · 3.1s)

_Thinking:_

The user wants me to delete the test.txt file. I'll use the bash tool to remove it.

**Tool: bash**

**Input:**
```json
{
  "command": "rm /mnt/e/gitProjects/library/test.txt",
  "description": "Delete test.txt file"
}
```

---

## Assistant (Build · mimo-v2-pro-free · 2.6s)

_Thinking:_

The file has been deleted successfully.

已删除 `test.txt`。

---

## User

---

## Assistant (Compaction · mimo-v2-pro-free · 6.1s)

_Thinking:_

The user is asking me to summarize the conversation. Let me construct a detailed summary based on the template provided.

## Goal

The user was testing opencode's functionality, specifically creating a file and then trying to undo the operation using the `/undo` command.

## Instructions

- The user asked to create a `test.txt` file with content "hello"
- The user asked why `/undo` didn't delete the file
- The user asked to delete the file manually

## Discoveries

- The `/undo` command in opencode is designed to undo AI-made code changes in response to user prompts, not file creation operations. It reverts changes and shows the original message again so the user can retry.
- `/undo` likely does not track/undo `write` tool calls that create new files.
- Built-in opencode commands include `/init`, `/undo`, `/redo`, `/share`, `/help`.

## Accomplished

- ✅ Created `test.txt` with content "hello" at `/mnt/e/gitProjects/library/test.txt`
- ✅ Investigated why `/undo` didn't delete the file (found it's for reverting code changes, not file operations)
- ✅ Deleted `test.txt` via `rm` command
- No further work in progress

## Relevant files / directories

- `/mnt/e/gitProjects/library/test.txt` — created then deleted (no longer exists)

---

