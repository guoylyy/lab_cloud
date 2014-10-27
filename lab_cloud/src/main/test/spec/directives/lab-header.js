'use strict';

describe('Directive: labHeader', function () {

  // load the directive's module
  beforeEach(module('srcApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<lab-header></lab-header>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the labHeader directive');
  }));
});
