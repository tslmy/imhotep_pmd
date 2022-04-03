# imhotep_pmd

[![Code style: black](https://img.shields.io/badge/code%20style-black-000000.svg)](https://github.com/psf/black)
[![pre-commit](https://img.shields.io/badge/pre--commit-enabled-brightgreen?logo=pre-commit&logoColor=white)](https://github.com/pre-commit/pre-commit)
[![CodeQL](https://github.com/tslmy/imhotep_pmd/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/tslmy/imhotep_pmd/actions/workflows/codeql-analysis.yml)

An [Imhotep][i] plugin for [PMD][p], the static analyzer.

[i]: https://github.com/justinabrahms/imhotep
[p]: https://pmd.github.io/

[PMD][p] talk to [Imhotep][i] via the [Static Analysis Results Interchange Format (sarif)][s], which is simply a JSON with a specific schema.

[s]: https://docs.oasis-open.org/sarif/sarif/v2.0/sarif-v2.0.html

## Installation

1. Install [Imhotep][i] itself from [PyPI](https://pypi.org/project/imhotep/), since `imhotep_pmd` is merely a binding/plugin for [Imhotep][i]:
   ```shell
   pip install imhotep
   ```
2. Install `imhotep_pmd` from [PyPI](https://pypi.org/project/imhotep-pmd/):
   ```shell
   pip install imhotep_pmd
   ```
3. Install [PMD][p]. Please refer to their website for instructions.

Unless you've unzipped the [PMD][p] archive file to `~/bin/pmd-bin-6.44.0/`, you'll have to tell `imhotep_pmd` where to find the executable. This can be achieved by providing a `imhotep_pmd.toml` at the root directory of the repo you want to run PMD against. The file should contain this line that specifies the command used to invoke [PMD][p]:

```toml
pmd_command = "~/bin/pmd-bin-6.44.0/bin/run.sh pmd"
```

## Usage

To use the plugin, you'll have to pass a path to the linter in question to the imhotep runtime.

```shell
imhotep --linter imhotep_pmd.plugin:PmdLinter
```

## Demo
Let's take #1 as an example.

```shell
$ imhotep \
   --repo_name="tslmy/imhotep_pmd" \
   --github-username="tslmy" \
   --github-password="$GITHUB_PASSWORD" \
   --pr-number=1 \
   --linter imhotep_pmd.plugin:PmdLinter
```

Screenshots:

| Before | After |
| ------- | --- |
| ![Screen Shot 2022-04-03 at 14 04 45](https://user-images.githubusercontent.com/594058/161449329-7c43d6d8-547e-43a6-ac21-57701cb3b8fd.png) | ![Screen Shot 2022-04-03 at 14 27 11](https://user-images.githubusercontent.com/594058/161449378-6c20f0df-9785-43a0-99da-67a07574bb8e.png) |
