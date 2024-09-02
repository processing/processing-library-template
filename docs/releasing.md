# Releasing your library




#### Using GitHub Actions for Library Release
You can automatically distribute the library using GitHub Actions. Libraries are distributed in `.zip` format to the [releases section](https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository) of the GitHub repository. The trigger to execute the release workflow is pushing a git tag that starts with `v`. For example:
```sh
git tag v0.0.1
git push origin v0.0.1
```


### Creating your website
A requirement for having your library listed officially is to have a stable documentation website.  
[Material for MkDocs](https://squidfunk.github.io/mkdocs-material/), is a straightforward way of creating content.
If your library is on Github, then you can take advantage of Github Pages to deploy and host the website.
We have configured a Github workflow at `.github/workflows/deploy-website.yml` that will process files
in the `docs` folder with [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/) and deploy it as a Github Page.

The configuration of the website is defined in the `mkdocs.yml` file. We have included some plugins, such
as search, code block copy and annotations, and navigation bars. The navigation menu is configured in
`mkdocs.yml` as well.

If you would like to preview your website locally, you will need to install [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/)
on your computer. please refer to the documentation.

1. You'll need to activate Github Pages and Github Actions for your project in the settings.
2. In settings > Github Pages, set the Source to "Deploy from a branch."
   Set the branch to `gh-pages` ; this branch is created by the
   command `mkdocs gh-deploy`. Serve from the root folder.
3. Edit the markdown files in the `docs`, and configure the navigation menu in `mkdocs.yml` file.
