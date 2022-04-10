# Prepare the environment.
FILE="mambaforge/envs/main/bin/python3"
if [ ! -f "${FILE}" ] || [ ! -r "${FILE}" ] || [ ! -x "${FILE}" ]; then
	echo "Environment does not exist."
	if [ ! -d "mambaforge" ]; then
		echo "mambaforge does not exist. Installing."
		curl -L -O "https://github.com/conda-forge/miniforge/releases/latest/download/Mambaforge-$(uname)-$(uname -m).sh"
		bash Mambaforge-$(uname)-$(uname -m).sh -b -u -p mambaforge
		rm "Mambaforge-$(uname)-$(uname -m).sh"
	fi
	mambaforge/bin/mamba create -n main python=3.10
fi

. mambaforge/etc/profile.d/conda.sh
conda activate main

# Ensure that PMD is installed.
# TODO: Encapsulate PMD into the workspace. Don't pollute the home directory.
FILE="~/bin/pmd-bin-6.44.0/bin/run.sh"
if [ ! -f "${FILE}" ] || [ ! -r "${FILE}" ] || [ ! -x "${FILE}" ]; then
	echo "PMD does not exist. Downloading."
	curl -L -O "https://github.com/pmd/pmd/releases/download/pmd_releases%2F6.44.0/pmd-bin-6.44.0.zip"
	unzip "pmd-bin-6.44.0.zip" -d ~/bin && rm "pmd-bin-6.44.0.zip"
fi

# Ensure that the Python packages are installed.
# shellcheck disable=SC1072,SC1073,SC1009
if [ ! pip3 list | grep "imhotep" ]; then
	echo "Imhotep is not installed. Installing with pip."
	# TODO: Update the release on PyPI, so that I don't have to install directly from GitHub.
	pip3 install git+https://github.com/justinabrahms/imhotep.git
fi

# shellcheck disable=SC1072,SC1073,SC1009
if [ ! pip3 list | grep "imhotep_pmd" ]; then
	echo "Imhotep_pmd is not installed. Installing with pip."
	# TODO: Update the release on PyPI, so that I don't have to install directly from GitHub.
	pip3 install git+https://github.com/tslmy/imhotep_pmd.git
fi

# Run the analysis.
imhotep \
	--repo_name="${REPO}" \
	--github-username="${GITHUB_USERNAME}" \
	--github-password="${GITHUB_PASSWORD}" \
	--pr-number="${ghprbPullId}" \
	--dir-override=. \
	--linter imhotep_pmd.plugin:PmdLinter
