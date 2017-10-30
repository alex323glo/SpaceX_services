var currentStoryIndex = 1;
var maxStoryIndex = 4;

function clickTriangleVisible() {
    console.log('Triangle clicked (current: ' + currentStoryIndex +'; max: ' + maxStoryIndex + ')!');

    if (currentStoryIndex === maxStoryIndex) {
        currentStoryIndex = 1;
    } else {
        currentStoryIndex++;
    }

    loadAjax(
        'story-text',
        'content/roof/story-text-' + currentStoryIndex + '.html'
    );
}