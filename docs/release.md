# Releasing your library

You've written your library, created examples, and tested it in Processing. Now you are ready to release 
and publish it.

You'll want to:

1. [create a documentation website](#creating-your-website), like the one you are reading now
2. create a [release on Github](#release-on-github)
3. Once you are ready to add your contribution to the Contribution Manager, 
email contributions@processing.org with the url to your release artifacts.


## Creating your website
A requirement for having your library listed officially is to have a stable documentation website.  
[Material for MkDocs](https://squidfunk.github.io/mkdocs-material/), is a straightforward way of creating content.

The configuration of the website is defined in the `mkdocs.yml` file. We have included some plugins, such
as search, code block copy and annotations, and navigation bars. The navigation menu is configured in
`mkdocs.yml` as well.

If your library is on Github, then you can take advantage of Github Pages to deploy and host the website.
We have configured a Github workflow at `.github/workflows/deploy-website.yml` that will process files
in the `docs` folder with [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/) and deploy it as a Github Page.
The steps to deploying your MkDocs website with Github pages are below:

1. You'll need to activate Github Pages and Github Actions for your project in the settings.
2. In settings > Github Pages, set the Source to "Deploy from a branch."
   Set the branch to `gh-pages`. Serve from the root folder.
3. Edit the markdown files in the `docs`, and configure the navigation menu in `mkdocs.yml` file.
4. Include the url of your website in the property named `url` in the `release.properties`. 


## Release on Github
Releasing on Github provides an easy reference for stable versions.
Github provides [documentation on releasing projects](https://docs.github.com/en/repositories/releasing-projects-on-github).
When creating your release, use a tag that starts with "v". This triggers a Github
workflow (`.github/workflows/release.yml`) that will automatically upload the necessary 
release artifacts, such as the `*.txt`, `*.zip`, `*.pdex` files in your `release` folder. 
Github by default will include compressed versions of your source code only.
